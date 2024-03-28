package com.example.saturn.models;

import com.example.saturn.models.enums.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
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
    private int orderQuantity;
    private int reservedQuantity;
    private RentInformation rentInformation;
    private String color;
    private String size;
    private String unitOfMeasure;
    private OrderItemStatus status;
    private LocalDate expiryDate;
}
