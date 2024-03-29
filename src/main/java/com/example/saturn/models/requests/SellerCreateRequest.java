package com.example.saturn.models.requests;

import com.example.saturn.models.enums.PaymentMethod;
import com.example.saturn.models.enums.SellerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
public class SellerCreateRequest {
    private int userId;
    @NotEmpty(message = "sellerName can not be null")
    private String sellerName;
    private String sellAdress;
    @NotEmpty(message = "payment methods can not be null")
    private List<PaymentMethod> acceptedPaymentMethods;
}
