package com.example.saturn.models;

import com.example.saturn.models.enums.PaymentMethodEnum;
import com.example.saturn.models.enums.SaleOrderStatus;
import com.example.saturn.models.enums.SaleType;
import com.example.saturn.models.enums.ShippingType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class SaleOrder {
    @Id
    private int id;
    private int userId;
    private String saleOrderCode;
    private String sellerCode;
    private String sellerName;
    private PaymentMethodEnum paymentMethodCode;
    private String paymentMethodName;
    private LocalDateTime createdTime;
    private LocalDateTime packedTime;
    private LocalDateTime returnedTime;
    private double totalAmount;
    private SaleType saleType;
    private SaleOrderStatus status;
    private ShippingType shippingType;
    private POD proofOfDelivery;
    private String deliveryAddress;
    private LocalDate estimatedShippingDate;
    private List<SaleOrderLog> trackingLogs;

}
