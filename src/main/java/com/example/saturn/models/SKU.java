package com.example.saturn.models;

import com.example.saturn.models.SKUVariety;
import com.example.saturn.models.enums.*;
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
//    ORDER/ IN-STOCK
    private ProductType productType;
//    only available to ORDER productType
    private int minimumFulfillmentDays;
//  only available to IN-STOCK productType, default 0 for other types
    private int stockQuantity;
//    RENT/ SELL
    private SaleType saleType;
    private RentInformation rentInformation;
    private List<SKUVariety> varietyList;
    private String unitOfMeasure;
    private Boolean isActive;
    private int maximumOrderQuantity;
    private SKUStatus status;
    private ProductCondition productCondition;
    private ShippingType shippingType;
//    not available when customer choose shipping type SELLER_DELIVERY
    private int shippingPrice;
    private int availableQuantity;
    private int reservedQuantity;
    private LocalDate expiryDate;
}
