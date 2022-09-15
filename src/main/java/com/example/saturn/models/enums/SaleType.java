package com.example.saturn.models.enums;

public enum SaleType {

   SELL("SELL"),
   RENT("RENT");


    private final String value;

    SaleType(String value){
        this.value = value;
    }
}
