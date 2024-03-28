package com.example.saturn.models.enums;

public enum DomainCode {
    SALE_ORDER("SO");

    private final String value;

    DomainCode(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
