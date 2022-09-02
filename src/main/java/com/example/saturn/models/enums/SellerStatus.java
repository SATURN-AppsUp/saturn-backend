package com.example.saturn.models.enums;


public enum SellerStatus {

    UNVERIFIED("UNVERIFIED"),
    VERIFYING("VERIFYING"),
    VERIFY_FAILED("VERIFY_FAILED"),
    DEACTIVATED("DEACTIVATED"),
    ACTIVATED("ACTIVATED"),
    STOPPED_COOPERATING("STOPPED_COOPERATING");


    private final String value;

    SellerStatus(String value){
        this.value = value;
    }
}