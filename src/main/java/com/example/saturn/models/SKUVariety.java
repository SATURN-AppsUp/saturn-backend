package com.example.saturn.models;


import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SKUVariety {
    private String color;
    private String size;
    private double unitPrice;
    private int availableQuantity;
    private boolean isDefault;

}
