package com.example.saturn.models.enums;


public enum OrderItemStatus {
    DRAFT("DRAFT"),
    RESERVING("RESERVING"),
    RESERVED("RESERVED");

    private final String value;

    OrderItemStatus(String value){
        this.value = value;
    }
}
