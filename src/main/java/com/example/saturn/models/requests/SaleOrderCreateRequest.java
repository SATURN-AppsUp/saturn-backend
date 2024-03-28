package com.example.saturn.models.requests;

import com.example.saturn.models.enums.SaleType;
import com.mongodb.lang.Nullable;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class SaleOrderCreateRequest {
    @NotNull
    @Min(0)
    private int userId;
//    @NotEmpty
//    private PaymentMethod paymentMethod;
    @Nullable
    private List<SaleOrderSKU> SKUList;
    @Nullable
    private SaleOrderSKU SKU;
//    @NotEmpty(message = "saleType must not be empty")
    private SaleType saleType;

//    @NotEmpty
//    private ShippingType shippingType;
}
