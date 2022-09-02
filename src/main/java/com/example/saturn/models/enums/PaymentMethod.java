package com.example.saturn.models.enums;

public enum PaymentMethod {
    COD("COD"),
    BANK("BANK");

    private final String value;

    PaymentMethod(String value){
        this.value = value;
    }
}
