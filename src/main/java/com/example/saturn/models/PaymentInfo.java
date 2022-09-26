package com.example.saturn.models;


import com.example.saturn.models.enums.PaymentMethodEnum;
import com.mongodb.lang.Nullable;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class PaymentInfo {
    @Id
    private int id;
    private PaymentMethodEnum paymentMethodEnum;
    @Nullable
    private String bankCode;
    @Nullable
    private String bankName;
    @Nullable
    private String bankFullName;
    @Nullable
    private Address deliveryAdress;
}
