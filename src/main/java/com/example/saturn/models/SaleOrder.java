package com.example.saturn.models;

import com.example.saturn.models.enums.PaymentMethod;
import com.example.saturn.models.enums.SaleOrderStatus;
import com.example.saturn.models.enums.SaleType;
import com.example.saturn.models.enums.ShippingType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class SaleOrder {
    @Id
    private int id;
    private int userId;
    private String saleOrderCode;
    private String sellerCode;
    private String sellerName;
    private PaymentMethod paymentMethod;
    private String paymentMethodName;
    private LocalDate createdTime;
    private LocalDate packedTime;
    private LocalDate returnedTime;
    private double totalAmount;
    private SaleType saleType;
    private SaleOrderStatus status;
    private ShippingType shippingType;
    private POD proofOfDelivery;

}
