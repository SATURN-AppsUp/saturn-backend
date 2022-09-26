package com.example.saturn.models.requests;

import com.example.saturn.models.enums.PaymentMethodEnum;
import com.example.saturn.models.enums.SellerStatus;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class SellerUpdateRequest {
    private String sellerName;
    @NotEmpty(message = "seller id can't be null")
    private int id;
    private String sellerCode;
    private String sellerAdress;
    private List<PaymentMethodEnum> acceptedPaymentMethodEnums;
    private SellerStatus status;

}

