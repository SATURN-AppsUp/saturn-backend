package com.example.saturn.models.requests;

import com.example.saturn.models.SKUVariety;
import com.example.saturn.models.enums.*;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.mongodb.lang.NonNullApi;
import com.mongodb.lang.Nullable;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
public class SKUCreateRequest {
    @NotEmpty
    private String sku;
    @NotEmpty
    private String sellerCode;
    @Nullable
    @JsonSetter(nulls = Nulls.SKIP)
    private String brand="";
    @NotEmpty
    private String name;
    @NotEmpty
    private String packaging;
    @Nullable
    private String categoryCode;
    @Nullable
    private String categoryName;
    @NotNull
    private ProductType productType;
    //    only availble to ORDER productType
    @Nullable
    @Min(0)
    private int minimumFulfillmentDays;
    //  only available to IN-STOCK productType, default 0 for other types
    @Nullable
    @JsonSetter(nulls = Nulls.SKIP)
    private int stockQuantity=0;
    @NotNull
    private SaleType saleType;
    @Nullable
    private RentInformation rentInformation;
    @NotEmpty
    private List<SKUVariety> varietyList;
    @NotNull
    private ProductCondition productCondition;
    @NotNull
    private ShippingType shippingType;
//    not available when customer choose shipping type CUSTOMER_DELIVERY
    @Nullable
    @Min(0)
    private int shippingPrice;
    @NotEmpty
    private String unitOfMeasure;
    @Nullable
//    use Boolean instead of boolean to have null values
    @JsonSetter(nulls = Nulls.SKIP)
    private Boolean isActive=false;
    @Nullable
    @JsonSetter(nulls =  Nulls.SKIP)
    private int maximumOrderQuantity=0;
    @Nullable
    @JsonSetter(nulls =  Nulls.SKIP)
    private int availableQuantity=0;
    @Nullable
    private LocalDate expiryDate;
}
