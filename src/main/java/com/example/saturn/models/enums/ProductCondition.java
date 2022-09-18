package com.example.saturn.models.enums;

public enum ProductCondition {
    NEW_SEALED("NEW_SEALED"),
    NEW_UNSEALED("NEW_UNSEALED"),
    NEW_LIKE("NEW_LIKE"),
    USED("USED");
    private final String value;

    ProductCondition(String value){
        this.value = value;
    }
}
