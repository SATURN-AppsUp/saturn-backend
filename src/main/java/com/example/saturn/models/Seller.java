package com.example.saturn.models;
import com.example.saturn.models.enums.PaymentMethodEnum;
import com.example.saturn.models.enums.SellerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document
public class Seller {
    private int userId;
    @Indexed
    @Id
    private int id;
    private String sellerCode;
    private String sellerName;
    private boolean isVerified;
    private String sellerAdress;
    private List<PaymentMethodEnum> acceptedPaymentMethodEnums;
    private SellerStatus status;

}
