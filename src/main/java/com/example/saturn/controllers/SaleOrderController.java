package com.example.saturn.controllers;

import com.example.saturn.models.requests.ApiResponse;
import com.example.saturn.models.requests.SaleOrderCreateRequest;
import com.example.saturn.services.SaleOrderService;
import com.example.saturn.utils.ApiResponseHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/sale-order")
public class SaleOrderController {


    private final SaleOrderService saleOrderService;
    @GetMapping
    public ResponseEntity getSaleOrders() {
        return ResponseEntity.ok("OK");
    }

    @PostMapping
    public ResponseEntity createSaleOrder(@Valid @RequestBody  SaleOrderCreateRequest request) {
        var createdOrder = saleOrderService.createSaleOrder(request);
        if (createdOrder == null) {
            return ApiResponseHandler.RespondError(HttpStatus.INTERNAL_SERVER_ERROR, "can't created order","SALE_ORDER_NOT_CREATED");
        }
        return ApiResponseHandler.Respond(HttpStatus.OK,"order created successfully", List.of(createdOrder));
    }

}
