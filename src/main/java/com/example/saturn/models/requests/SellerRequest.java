package com.example.saturn.models.requests;

import com.example.saturn.models.enums.PaymentMethod;
import com.example.saturn.models.enums.SellerStatus;
import com.mongodb.lang.Nullable;

public class SellerRequest {
    @Nullable
    private String sellerCode;
    @Nullable
    private String sellerName;
    @Nullable
    private String userId;
    @Nullable
    private boolean isVerified;
    @Nullable
    private String sellerAddress;
    @Nullable
    private SellerStatus status;
    @Nullable
    private PaymentMethod paymentMethod;
}
