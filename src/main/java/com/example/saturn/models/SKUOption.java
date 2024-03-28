package com.example.saturn.models;


import lombok.Data;

import java.util.List;

@Data
public class SKUOption {
    private List<Category> categoryList;
    private List<String> brand;
    private List<String> shippingType;
}
