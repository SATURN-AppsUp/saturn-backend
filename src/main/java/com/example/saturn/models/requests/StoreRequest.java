package com.example.saturn.models.requests;


import lombok.Data;

@Data
public class StoreRequest {
    private String sellerCode;
    private String storeName;
    private String storeCode;
}
