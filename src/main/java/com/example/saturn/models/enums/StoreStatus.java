package com.example.saturn.models.enums;

public enum StoreStatus {
    PUBLISHED("PUBLISHED"),
    HALTED_SALE("HALTED_SALE"),
    DISCONTINUED("DISCONTINUED");

    private final String value;

    StoreStatus(String value) {
        this.value = value;
    }
}
