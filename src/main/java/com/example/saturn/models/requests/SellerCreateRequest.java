package com.example.saturn.models.requests;

import com.example.saturn.models.enums.PaymentMethodEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class SellerCreateRequest {
    @NotEmpty(message = "username can not be null")
    private String username;
    @NotEmpty(message = "sellerName can not be null")
    private String sellerName;
    private String sellerAdress;
    @NotEmpty(message = "payment methods can not be null")
    private List<PaymentMethodEnum> acceptedPaymentMethodEnums;
}
