package com.example.saturn.models.enums;

public enum ProductType {
    ORDER("ORDER"),
    IN_STOCK("IN-STOCK");

    private final String value;

    ProductType(String value){
        this.value = value;
    }

}
