package com.example.saturn.services;


import com.example.saturn.models.*;
import com.example.saturn.models.dao.PaymentMethodDAO;
import com.example.saturn.models.enums.*;
import com.example.saturn.models.requests.*;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
@AllArgsConstructor
public class SaleOrderService {

    private final MongoTemplate template;
    private final GenIdService genIdService;

    public SaleOrder confirmSaleOrder(SaleOrderConfirmRequest request, int userId) {
        var seller = template.findOne(Query.query(where("sellerCode").is(request.getSellerCode())),Seller.class);
        var saleOrder = template.findOne(Query.query(where("saleOrderCode").is(request.getSaleOrderCode())),SaleOrder.class);
        if (seller == null) {
            throw new IllegalArgumentException("not found any seller");
        }

        if (saleOrder == null) {
            throw new IllegalArgumentException("not found any sale order");
        }

        if (request.getEstimatedDeliveryDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("delivery date must be after today");
        }

        if (saleOrder.getStatus() != SaleOrderStatus.WAIT_TO_CONFIRM) {
            throw new IllegalArgumentException("only WAIT_TO_CONFIRM orders can be processed");
        }

        var confirmLog = new SaleOrderLog(SaleOrderStatus.CONFIRMED, userId, LocalDateTime.now());

        List<SaleOrderLog> SOLog = saleOrder.getTrackingLogs() != null  ? saleOrder.getTrackingLogs() : new ArrayList<>();
        SOLog.add(confirmLog);
        saleOrder.setTrackingLogs(SOLog);
        saleOrder.setStatus(SaleOrderStatus.CONFIRMED);
        return template.save(saleOrder,"saleOrder");
    }

    public SaleOrder checkoutSaleorder(SaleOrderCheckoutRequest request, int userId) {
        if (userId <=0) {
            throw new IllegalArgumentException("userId is invalid");
        }
        var saleOrder = template.findOne(Query.query(
                where("saleOrderCode").is(request.getSaleOrderCode()).
                        andOperator(
                                where("userId").is(userId))),SaleOrder.class);
        if (saleOrder == null) {
            throw new IllegalArgumentException("not found any matched sale order code");
        }
        var paymentMethod = PaymentMethodDAO.getPaymentInfo(request.getPaymentMethodCode());
        if (paymentMethod == null) {
            throw new IllegalArgumentException("not found any payment method");
        }
        if (saleOrder.getStatus() != SaleOrderStatus.DRAFT) {
            throw new IllegalArgumentException("sale order status is invalid (must be DRAFT to checkout)");
        }
        saleOrder.setPaymentMethodCode(paymentMethod.getMethodCode());
        saleOrder.setPaymentMethodName(paymentMethod.getMethodName());
        saleOrder.setDeliveryAddress(request.getDeliveryAddress());
        saleOrder.setStatus(SaleOrderStatus.WAIT_TO_CONFIRM);
        return template.save(saleOrder,"saleOrder");

    }
    public List<SaleOrder> getSaleOrders(SaleOrderRequest request) {
        var query = new Query();
        if (request.getUserId() > 0) {
            query.addCriteria(where("userId").is(request.getUserId()));
        }

        if (request.getSaleOrderCode() != null) {
            query.addCriteria(where("saleOrderCode").is(request.getSaleOrderCode()));
        }

        if (request.getPaymentMethod() != null) {
            var paymentCode = PaymentMethodEnum.valueOf(request.getPaymentMethod());
            query.addCriteria(where("paymentMethodCode").is(paymentCode));
        }

        if (request.getSaleType() != null) {
            var saleType = SaleType.valueOf(request.getSaleType());
            query.addCriteria(where("saleType").is(saleType));
        }

        if (request.getStatus() != null) {
            var status = SaleOrderStatus.valueOf(request.getStatus());
            query.addCriteria(where("status").is(status));
        }

        if (request.getShippingType() != null) {
            var shippingType = PaymentMethodEnum.valueOf(request.getShippingType());
            query.addCriteria(where("shippingType").is(shippingType));
        }

        if (query.getQueryObject().size() == 0){
            return template.findAll(SaleOrder.class);
        }

        return template.find(query,SaleOrder.class);
    }
    public SaleOrder createSaleOrder(SaleOrderCreateRequest request) {
        if (request.getUserId() <= 0) {
            throw new IllegalArgumentException("userId is not valid");
        }
        var existsUser = template.exists(Query.query(where("id").is(request.getUserId())), Account.class);
        if (!existsUser)
        {
            throw new IllegalArgumentException("user with this id does not exist");
        }

        if (request.getSKU() == null) {
            throw new IllegalArgumentException("List SKU must not be empty");
        }

        var sku = template.findOne(Query.query(where("sku").is(request.getSKU().getSKU())),SKU.class);

        if (sku == null) {
            throw new IllegalArgumentException("Not found any SKU");
        }
        if (sku.getAvailableQuantity() == 0 && sku.getProductType() == ProductType.IN_STOCK) {
            throw new IllegalArgumentException("product is out of stock");
        } else if (sku.getAvailableQuantity() - request.getSKU().getQuantity() < 0 ) {
            throw new IllegalArgumentException("There is not enough available quantity in-stock to purchase");
        }

        var sellerCode = request.getSKU().getSKU().split("\\.")[0];
//      check if user has orders with this seller
        var seller = template.findOne(Query.query(where("sellerCode").is(sellerCode)),Seller.class);
        if (seller == null) {
            throw new IllegalArgumentException("Not found any matched seller");
        }

        var orderWithSeller = template.findOne(Query.query(
                where("sellerCode").is(sellerCode).
                        andOperator(
                                where("userId").is(request.getUserId()),
                                where("status").is(SaleOrderStatus.DRAFT)
                        )),SaleOrder.class);

        if (orderWithSeller != null) {
//          check if sku already in this order
            var saleOrderItem = template.findOne(Query.query(where("sku").is(sku.getSku()).andOperator(where("saleOrderCode").is(orderWithSeller.getSaleOrderCode()))),SaleOrderItem.class);
            if (saleOrderItem != null) {
                throw new IllegalArgumentException("This product has already been placed for order");
            }
            addSaleOrderItems(request.getSKU(),orderWithSeller.getSaleOrderCode());
            return orderWithSeller;
        }
        else {
            var createdLog = new SaleOrderLog(SaleOrderStatus.DRAFT,request.getUserId(),LocalDateTime.now());
            var newSO = new SaleOrder(
                    genIdService.genNextId("SALE_ORDER"),
                    request.getUserId(),
                    genIdService.genNextCode(DomainCode.SALE_ORDER.value()),
                    seller.getSellerCode(),
                    seller.getSellerName(),
                    null,
                    null,
                    LocalDateTime.now(),
                    null,
                    null,
                    request.getSKU().getQuantity() * sku.getUnitPrice(),
                    request.getSaleType(),
                    SaleOrderStatus.DRAFT,
                    null,
                    null,
                    null,
                    null,
                    List.of(createdLog)
                    );
            var createdOrder = template.insert(newSO);
            addSaleOrderItems(request.getSKU(),createdOrder.getSaleOrderCode());
            return createdOrder;
        }
//        request.getSKUList().forEach(sku -> {
////            check if user already have orders with that seller of item;
//            var SKUInfo = template.findOne(Query.query(where("sku").is(sku())),SKU.class);
//            if (SKUInfo == null || SKUInfo.getId() > 0) {
//
//            }
//        });

    }
    public SaleOrderItem addSaleOrderItems(SaleOrderSKU item, String saleOrderCode) {
        var sku = template.findOne(Query.query(where("sku").is(item.getSKU())),SKU.class);
        if (sku == null) {
            throw new IllegalArgumentException("Not found any SKU");
        }
        if (sku.getAvailableQuantity() == 0 && sku.getProductType() == ProductType.IN_STOCK) {
            throw new IllegalArgumentException("product is out of stock");
        }
        else if (sku.getAvailableQuantity() - item.getQuantity() < 0 ) {
            throw new IllegalArgumentException("There is not enough available quantity in-stock to purchase");
        }
        sku.setAvailableQuantity(sku.getAvailableQuantity()-1);
        template.save(sku);
        var SOItem = new SaleOrderItem(
                genIdService.genNextId("SALE_ORDER_ITEM"),
                item.getSKU(),
                saleOrderCode,
                sku.getSellerCode(),
                sku.getBrand(),
                sku.getName(),
                sku.getPackaging(),
                sku.getCategoryCode(),
                sku.getCategoryName(),
                item.getQuantity(),
                0,
                null,
                item.getColor(),
                item.getSize(),
                sku.getUnitOfMeasure(),
                OrderItemStatus.DRAFT,
                null
               );
        return template.insert(SOItem);
    }
}
