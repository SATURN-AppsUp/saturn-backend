package com.example.saturn.models.requests;

import com.example.saturn.models.SKUVariety;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaleOrderSKU {
    private String SKU;
    private String color;
    private String size;
    private int quantity;
}
