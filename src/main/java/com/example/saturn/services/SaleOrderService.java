package com.example.saturn.services;


import com.example.saturn.models.*;
import com.example.saturn.models.enums.DomainCode;
import com.example.saturn.models.enums.OrderItemStatus;
import com.example.saturn.models.enums.SKUStatus;
import com.example.saturn.models.enums.SaleOrderStatus;
import com.example.saturn.models.requests.SKURequest;
import com.example.saturn.models.requests.SaleOrderCreateRequest;
import com.example.saturn.models.requests.SaleOrderSKU;
import lombok.AllArgsConstructor;
import org.hibernate.validator.internal.util.DomainNameUtil;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
@AllArgsConstructor
public class SaleOrderService {

    private final MongoTemplate template;
    private final SKUService skuService;
    private final SellerService sellerService;
    private final GenIdService genIdService;

    public SaleOrder createSaleOrder(SaleOrderCreateRequest request) {
        System.out.println(request);
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

        var createdOrders = List.of();
        var sellerCode = request.getSKU().getSKU().split("\\.")[0];
//      check if user has orders with this seller
        System.out.println(sellerCode);
        var seller = template.findOne(Query.query(where("sellerCode").is(sellerCode)),Seller.class);

        if (seller == null) {
            throw new IllegalArgumentException("Not found any matched seller");
        }

        var sku = template.findOne(Query.query(where("sku").is(request.getSKU().getSKU())),SKU.class);

        if (sku == null) {
            throw new IllegalArgumentException("Not found any SKU");
        }


        var orderWithSeller = template.findOne(Query.query(where("sellerCode").is(sellerCode)),SaleOrder.class);

        if (orderWithSeller != null) {
            addSaleOrderItems(request.getSKU(),orderWithSeller.getSaleOrderCode());
            return orderWithSeller;
        }
        else {
            var newSO = new SaleOrder(
                    genIdService.genNextId("SALE_ORDER"),
                    request.getUserId(),
                    genIdService.genNextCode(DomainCode.SALE_ORDER.value()),
                    seller.getSellerCode(),
                    seller.getSellerName(),
                    null,
                    null,
                    LocalDate.now(),
                    null,
                    null,
                    request.getSKU().getQuantity() * sku.getUnitPrice(),
                    request.getSaleType(),
                    SaleOrderStatus.DRAFT,
                    null,
                    null
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
    public List<SaleOrder> getSaleOrders(SaleOrder saleOrder) {
        return List.of();
    }
    public SaleOrderItem addSaleOrderItems(SaleOrderSKU item, String saleOrderCode) {
        var sku = template.findOne(Query.query(where("sku").is(item.getSKU())),SKU.class);
        if (sku == null) {
            throw new IllegalArgumentException("Not found any SKU");
        }
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
        var insertedSOItem = template.insert(SOItem);
        return insertedSOItem;
    }
}
