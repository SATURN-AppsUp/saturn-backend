package com.example.saturn.models.enums;

public enum ShippingType {
    SELLER_DELIVERY("SELLER_DELIVERY"),
    CUSTOMER_DELIVERY("SELLER_DELIVERY"),
    TPL_DELIVERY("TPL_DELIVERY");

    private final String value;

    ShippingType(String value){
        this.value = value;
    }
}
