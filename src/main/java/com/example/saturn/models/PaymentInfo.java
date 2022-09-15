package com.example.saturn.models;


import com.example.saturn.models.enums.AccountStatus;
import com.example.saturn.models.enums.PaymentMethod;
import com.mongodb.lang.Nullable;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class PaymentInfo {
    @Id
    private int id;
    private PaymentMethod paymentMethod;
    @Nullable
    private String bankCode;
    @Nullable
    private String bankName;
    @Nullable
    private String bankFullName;
    @Nullable
    private Address deliveryAdress;
}
