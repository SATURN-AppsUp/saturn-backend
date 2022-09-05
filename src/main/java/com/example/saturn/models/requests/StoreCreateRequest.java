package com.example.saturn.models.requests;

import com.example.saturn.models.enums.StoreStatus;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;

@Data
public class StoreCreateRequest {
    @NotEmpty(message = "seller id can't be empty")
    private int sellerId;
    @NotEmpty(message = "store's name can't be null")
    private String storeName;
    private String storeBannerImageUrl;
}
