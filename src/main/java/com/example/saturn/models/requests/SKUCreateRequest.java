package com.example.saturn.models.requests;

import com.example.saturn.models.SKUVariety;
import com.example.saturn.models.enums.SKUStatus;
import com.example.saturn.models.enums.SaleType;
import com.mongodb.lang.NonNullApi;
import com.mongodb.lang.Nullable;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@Data
public class SKUCreateRequest {
    @NotEmpty
    private String sku;
    @NotEmpty
    private String sellerCode;
    @Nullable
    private String brand;
    @NotEmpty
    private String name;
    @NotEmpty
    private String packaging;
    @Nullable
    private String categoryCode;
    @NotEmpty
    private String productType;
    @NotEmpty
    private SaleType saleType;
    @NotEmpty
    private List<SKUVariety> varietyList;
    @NotEmpty
    private String unitOfMeasure;
    @Nullable
//    use Boolean instead of boolean to have null values
    private Boolean isActive;
    private int maximumOrderQuantity=0;
    @NotEmpty
    private int availableQuantity;
    @Nullable
    private LocalDate expiryDate;
}
