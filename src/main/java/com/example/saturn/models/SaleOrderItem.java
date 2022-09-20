package com.example.saturn.models;

import com.example.saturn.models.enums.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDate;
import java.util.List;

public class SaleOrderItem {
    @Id
    private int id;
    private String sku;
    private String saleOrderCode;
    private String sellerCode;
    private String brand;
    private String name;
    private String packaging;
    private String categoryCode;
    private String categoryName;
    //    ORDER/ IN-STOCK
    private ProductType productType;
    private int orderQuantity;
    private int reservedQuantity;
    private RentInformation rentInformation;
    private List<SKUVariety> varietyList;
    private String unitOfMeasure;
    private SKUStatus status;
    private ProductCondition productCondition;
    private LocalDate expiryDate;
}
