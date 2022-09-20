package com.example.saturn.models.enums;


public enum SaleOrderStatus {
    DRAFT("DRAFT"),
    WAIT_TO_CONFIRM("WAIT_TO_CONFIRM"),
    CONFIRMED("CONFIRMED"),
    RESERVING("RESERVING"),
    RESERVED("RESERVED"),
    PACKING("PACKING"),
    DELIVERING("DELIVERING"),
    DELIVERED("DELIVERED"),
    RETURNED("RETURNED"),
    CANCELED("CANCELED");

    private String value;

    SaleOrderStatus(String value){
        this.value = value;
    }
}
