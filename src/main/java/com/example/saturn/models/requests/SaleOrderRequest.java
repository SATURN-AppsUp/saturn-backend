package com.example.saturn.models.requests;

import com.example.saturn.models.enums.PaymentMethodEnum;
import com.example.saturn.models.enums.SaleOrderStatus;
import com.example.saturn.models.enums.SaleType;
import com.example.saturn.models.enums.ShippingType;
import com.mongodb.lang.Nullable;
import lombok.Data;

@Data
public class SaleOrderRequest {
    @Nullable
    private int userId;
    @Nullable
    private String sellerCode;
    @Nullable
    private String saleOrderCode;
    @Nullable
    private String paymentMethod;
    @Nullable
    private String saleType;
    @Nullable
    private String status;
    @Nullable
    private String shippingType;
}
