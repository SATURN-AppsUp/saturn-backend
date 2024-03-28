package com.example.saturn.models.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class SaleOrderConfirmRequest {
    @NotEmpty(message = "seller code must not be null")
    private String sellerCode;
    @NotEmpty(message = "sale order code must not be null")
    private String saleOrderCode;
    @NotNull
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate estimatedDeliveryDate;
}
