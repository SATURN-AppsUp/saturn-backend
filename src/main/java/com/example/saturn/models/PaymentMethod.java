package com.example.saturn.models;

import com.example.saturn.models.enums.PaymentMethodEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentMethod {
    private PaymentMethodEnum methodCode;
    private String methodName;
}
