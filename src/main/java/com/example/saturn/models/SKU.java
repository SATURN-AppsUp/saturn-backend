package com.example.saturn.models;

import com.example.saturn.models.SKUVariety;
import com.example.saturn.models.enums.SKUStatus;
import com.example.saturn.models.enums.SaleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class SKU {
    @Id
    private int id;
    @Indexed
    private String sku;
    private String sellerCode;
    private String brand;
    private String name;
    private String packaging;
    private String categoryCode;
    private String categoryName;
    private String productType;
    private SaleType saleType;
    private List<SKUVariety> varietyList;
    private String unitOfMeasure;
    private Boolean isActive;
    private int maximumOrderQuantity;
    private SKUStatus status;
    private int availableQuantity;
    private int reservedQuantity;
    private LocalDate expiryDate;
}
