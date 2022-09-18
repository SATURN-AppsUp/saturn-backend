package com.example.saturn.services;


import com.example.saturn.models.SKU;
import com.example.saturn.models.SKUVariety;
import com.example.saturn.models.Seller;
import com.example.saturn.models.enums.ProductType;
import com.example.saturn.models.enums.SKUStatus;
import com.example.saturn.models.enums.SaleType;
import com.example.saturn.models.enums.ShippingType;
import com.example.saturn.models.requests.SKUCreateRequest;
import com.example.saturn.models.requests.SKURequest;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
@AllArgsConstructor
public class SKUService {

    private final MongoTemplate template;
    private final GenIdService genIdService;

    public List<SKU> getSKUs(SKURequest sku) {
        var query = new Query();
        if (!sku.getSku().isEmpty()) {
            query.addCriteria(where("sku").is(sku.getSku()));
        }
        if (!sku.getSellerCode().isEmpty()) {
            query.addCriteria(where("sellerCode").is(sku.getSellerCode()));

        }
        if (!sku.getName().isEmpty())
        {
            query.addCriteria(where("name").is(sku.getName()));

        }

        if (!sku.getCategoryCode().isEmpty())
        {
            query.addCriteria(where("categoryCode").is(sku.getCategoryCode()));

        }
        if (!sku.getProductType().isEmpty())
        {
            query.addCriteria(where("productType").is(sku.getProductType()));

        }
        if (sku.getShippingType() != null)
        {
            query.addCriteria(where("shippingType").is(sku.getShippingType()));

        }
        if (sku.getProductCondition() != null)
        {
            query.addCriteria(where("productCondition").is(sku.getProductCondition()));
        }
        if (sku.getIsActive() != null)
        {
            query.addCriteria(where("isActive").is(sku.getIsActive()));

        }
        return template.find(query,SKU.class);
    }
    public SKU createSKU(SKUCreateRequest sku) {
//      validate sku from sellerCode

        if (!sku.getSku().isEmpty())
        {
            var sellerCode = sku.getSku().split(".")[0];
            if (sellerCode != sku.getSellerCode()) {
                throw new IllegalArgumentException("sku and seller have different sellerCode");
            }
            else {
                var seller = template.findOne(Query.query(where("sellerCode").is(sku.getSellerCode())), Seller.class);
                if (seller == null) {
                    throw new IllegalArgumentException("seller doesn't exist");
                }
            }
        }
        if (sku.getProductType() == ProductType.ORDER) {
            sku.setStockQuantity(0);
            if (sku.getMinimumFulfillmentDays() <= 0) {
                throw new IllegalArgumentException("minimumFulfillmentDays must be greater than 0");
            }
        }
        else if (sku.getProductType() == ProductType.IN_STOCK) {
            sku.setMinimumFulfillmentDays(0);
        }

//        validate rent condition
        if (sku.getSaleType() == SaleType.RENT){
            if (sku.getRentInformation() == null || !sku.getRentInformation().isValid()) {
                throw new IllegalArgumentException("rent information must not be null");
            }
            else {
                if (sku.getRentInformation().getMaximumRentDay() < sku.getRentInformation().getMinimumRentDays()){
                    throw new IllegalArgumentException("maximum rent days can't be less than minimum rent days");
                }

                if (sku.getRentInformation().getProductReturnConditions().size() > 5) {
                    throw new IllegalArgumentException("You can only have up to 5 conditions");
                }
                else {
                    for (int i=0; i < sku.getRentInformation().getProductReturnConditions().size(); i++)
                    {
                        var c = sku.getRentInformation().getProductReturnConditions().get(i);
                        if (c.isBlank()) {
                            throw new IllegalArgumentException(String.format("condition %d is empty",i));
                        }
                    }
                }
//                validate price
                if (sku.getRentInformation().getRentPricePerDay() <=0) {
                    throw new IllegalArgumentException("rent price must be larger than 0");
                }
            }
        }

//       validate variety list
        if (sku.getVarietyList().size() == 0) {
            throw new IllegalArgumentException("Variety list can't be null");
        }
        else {
           var defaultVariety =  sku.getVarietyList().stream().filter(SKUVariety::isDefault).findFirst().orElseThrow(() -> new IllegalArgumentException("variety list must have at least one default variety"));
           if (defaultVariety.isValid()) {
               throw new IllegalArgumentException("default variety's is not valid (unit price & quantity >= 0");
           }
        }

//       validate shipping type & price
        if (sku.getShippingType() == ShippingType.CUSTOMER_DELIVERY)
        {
            sku.setShippingPrice(0);
        }

        if (sku.getAvailableQuantity() < sku.getMaximumOrderQuantity()) {
            throw new IllegalArgumentException("maxmimum order quantity must be less than available quantity");
        }

        if (sku.getExpiryDate() != null && sku.getExpiryDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("expiry date must be after current date ");
        }

        if (sku.getIsActive() == null){
            sku.setIsActive(false);
        }
        var skuStatus = sku.getIsActive() ? SKUStatus.SELLING : SKUStatus.DRAFT;
        var createdSKU = new SKU(
                genIdService.genNextId("sku"),
                sku.getSku(),
                sku.getSellerCode(),
                sku.getBrand(),
                sku.getName(),
                sku.getPackaging(),
                sku.getCategoryCode(),
                "TEMP_CATEGORY_NAME",
                sku.getProductType(),
                sku.getMinimumFulfillmentDays(),
                sku.getStockQuantity(),
                sku.getSaleType(),
                sku.getRentInformation(),
                sku.getVarietyList(),
                sku.getUnitOfMeasure(),
                sku.getIsActive(),
                sku.getMaximumOrderQuantity(),
                skuStatus,
                sku.getProductCondition(),
                sku.getShippingType(),
                sku.getShippingPrice(),
                sku.getAvailableQuantity(),
                0,
                sku.getExpiryDate()
                );
        return template.insert(createdSKU);
    }

}
