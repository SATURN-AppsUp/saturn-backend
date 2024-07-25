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

    private int sellerId;

    private String sellerCode;

    private String accountNumber;

    @Nullable
    private String bankCode;
    @Nullable
    private String bankName;
    @Nullable
    private String bankFullName;
}
