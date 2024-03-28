package com.example.saturn.models.requests;

import com.example.saturn.models.enums.PaymentMethodEnum;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SaleOrderCheckoutRequest {
    @NotEmpty(message = "sale order code must not be null")
    private String saleOrderCode;
    @NotEmpty(message = "delivery address must not be null")
    private String deliveryAddress;
    @NotEmpty(message = "payment method must not be null")
    private String paymentMethodCode;
}
