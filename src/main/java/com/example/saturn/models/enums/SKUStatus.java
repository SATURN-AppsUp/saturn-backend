package com.example.saturn.models.enums;

public enum SKUStatus {
    DRAFT("DRAFT"),
    SELLING("SELLING"),
    LIMITED_SALE("LIMITED_SALE"),
    OUT_OF_STOCK("OUT_OF_STOCK"),
    DISCONTINUED("DISCONTINUED");

    private final String value;

    SKUStatus(String value) {
        this.value = value;
    }

}
