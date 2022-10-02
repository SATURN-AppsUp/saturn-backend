package com.example.saturn.models.requests;

import com.example.saturn.models.enums.ProductCondition;
import com.example.saturn.models.enums.SaleType;
import com.example.saturn.models.enums.ShippingType;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.mongodb.lang.Nullable;
import lombok.Data;

@Data
public class SKURequest {
    @Nullable
    @JsonSetter(nulls =  Nulls.SKIP)
    private String sku="";
    @Nullable
    @JsonSetter(nulls = Nulls.SKIP)
    private String sellerCode="";
    @Nullable
    private String brand="";
    @Nullable
    private String name="";
    @Nullable
    private String categoryCode="";
    @Nullable
    private String productType="";
    @Nullable
    private SaleType saleType;
    @Nullable
    private ShippingType shippingType;
    @Nullable
    private ProductCondition productCondition;
    @Nullable
    @JsonSetter(nulls =  Nulls.SKIP)
    private String unitOfMeasure="";
    @Nullable
//    use Boolean instead of boolean to have null values
    private Boolean isActive;
    @Nullable
    private int maximumOrderQuantity=0;
    @Nullable
    private int availableQuantity=0 ;
}
