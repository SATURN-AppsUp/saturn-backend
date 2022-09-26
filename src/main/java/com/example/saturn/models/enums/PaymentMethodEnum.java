package com.example.saturn.models.enums;

public enum PaymentMethodEnum {
    COD("COD"),
    BANK("BANK");

    private final String value;



    PaymentMethodEnum(String value){
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
