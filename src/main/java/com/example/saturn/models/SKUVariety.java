package com.example.saturn.models;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SKUVariety {
    private String color;
    private String size;
    private double unitPrice;
    private double tax;
    private int availableQuantity;
    private boolean isDefault;
}
