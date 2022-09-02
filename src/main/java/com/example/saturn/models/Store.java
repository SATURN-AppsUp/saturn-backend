package com.example.saturn.models;

import com.example.saturn.models.enums.SellerStatus;
import com.example.saturn.models.enums.StoreStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document
public class Store {
    private int sellerId;
    private int sellerCode;
    @Id
    private int storeId;
    private String storeName;
    private String storeCode;
    private String storeBannerImg;
    private StoreStatus storeStatus;

}
