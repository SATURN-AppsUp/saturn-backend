package com.example.saturn.models.requests;

import com.example.saturn.models.enums.PaymentMethodEnum;
import com.example.saturn.models.enums.SellerStatus;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.mongodb.lang.Nullable;
import lombok.Data;

import java.util.List;
@Data
public class SellerRequest {
    @Nullable
    @JsonSetter(nulls =  Nulls.SKIP)
    private String sellerCode="";
    @Nullable
    @JsonSetter(nulls =  Nulls.SKIP)
    private String sellerName="";
    @Nullable
    private String userId;
    @Nullable
    private Boolean isVerified;
    @Nullable
    private SellerStatus status;
    @Nullable
    private List<PaymentMethodEnum> paymentMethodEnums;
}
