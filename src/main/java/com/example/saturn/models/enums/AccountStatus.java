package com.example.saturn.models.enums;


public enum AccountStatus {

    UNVERIFIED("UNVERIFIED"),
    VERIFYING("VERIFYING"),
    VERIFIED("VERIFIED"),
    VERIFY_FAILED("VERIFY_FAILED"),
    DEACTIVATED("DEACTIVATED"),
    ACTIVATED("ACTIVATED");



    private final String value;

    AccountStatus(String value){
        this.value = value;
    }
}