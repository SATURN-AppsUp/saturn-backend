package com.example.saturn.models.requests;

import com.example.saturn.models.POD;
import com.example.saturn.models.SKU;
import com.example.saturn.models.SKUVariety;
import com.example.saturn.models.enums.PaymentMethod;
import com.example.saturn.models.enums.SaleOrderStatus;
import com.example.saturn.models.enums.SaleType;
import com.example.saturn.models.enums.ShippingType;
import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
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
