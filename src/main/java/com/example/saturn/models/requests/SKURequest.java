package com.example.saturn.models.requests;

import com.example.saturn.models.SKUVariety;
import com.example.saturn.models.enums.SKUStatus;
import com.example.saturn.models.enums.SaleType;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.mongodb.lang.NonNullApi;
import com.mongodb.lang.Nullable;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@Data
public class SKURequest {
    @Nullable
    @JsonSetter(nulls =  Nulls.SKIP)
    private String sku="";
    @JsonSetter(nulls =  Nulls.SKIP)
    private String sellerCode="";
    @Nullable
    @JsonSetter(nulls =  Nulls.SKIP)
    private String brand="";
    @NotEmpty
    private String name="";
    @NotEmpty
    private String packaging="";
    @Nullable
    private String categoryCode="";
    @Nullable
    private String productType="";
    @Nullable
    private SaleType saleType;
    @Nullable
    @JsonSetter(nulls =  Nulls.SKIP)
    private String unitOfMeasure="";
    @Nullable
//    use Boolean instead of boolean to have null values
    private Boolean isActive;
    private int maximumOrderQuantity=0;
    @Nullable
    private int availableQuantity=0 ;
}
