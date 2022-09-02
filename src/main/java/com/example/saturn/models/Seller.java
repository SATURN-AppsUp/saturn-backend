package com.example.saturn.models;
import com.example.saturn.models.enums.AccountStatus;
import com.example.saturn.models.enums.PaymentMethod;
import com.example.saturn.models.enums.SellerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.time.LocalDate;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Document
public class Seller {
    @Id
    private int userId;
    @Indexed
    private int id;
    @NotEmpty(message = "sellerCode can not be null")
    private String sellerCode;
    @NotEmpty(message = "sellerName can not be null")
    private String sellerName;
    private int inactiveDays=0;
    @NotEmpty
    private boolean isVerified;
    private String sellAdress;
    private List<PaymentMethod> acceptedPaymentMethods;
    private SellerStatus status;

}
