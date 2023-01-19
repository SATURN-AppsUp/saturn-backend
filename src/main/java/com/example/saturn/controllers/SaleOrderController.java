package com.example.saturn.controllers;

import com.example.saturn.models.requests.*;
import com.example.saturn.services.SaleOrderService;
import com.example.saturn.utils.ApiResponseHandler;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@AllArgsConstructor
@RestController
@RequestMapping("/api/sale-order")
public class SaleOrderController {


    private final SaleOrderService saleOrderService;


    @PostMapping("confirmation")
    public ResponseEntity confirmSaleOrder(@Valid @RequestBody SaleOrderConfirmRequest request, @RequestHeader Map<String, String> headers) {
        int userId = Integer.parseInt(headers.get("userid"));
        var confirmedOrder = saleOrderService.confirmSaleOrder(request, userId);
        return ApiResponseHandler.Respond(HttpStatus.OK, "successfully confirmed order", List.of(confirmedOrder));
    }

    @PostMapping("checkout")
    public ResponseEntity checkoutSaleOrder(@Valid @RequestBody  SaleOrderCheckoutRequest request, @RequestHeader Map<String, String> headers) {
        var checkedOutOrder = saleOrderService.checkoutSaleorder(request, Integer.parseInt(headers.get("userid")));
        return ApiResponseHandler.Respond(HttpStatus.OK, "successfully checked out order", List.of(checkedOutOrder));

    }

//    @GetMapping("test-queue")
//    public ResponseEntity getTestQueue() {
//        return ApiResponseHandler.Respond(HttpStatus.OK, "OK", saleOrderService.getTestQueue());
//    }
//
//    @PostMapping("test-queue")
//    public ResponseEntity postTestQueue(@RequestBody TestSo so) {
//        saleOrderService.insertTestQueue(so.getSaleOrderCode());
//        return ApiResponseHandler.Respond(HttpStatus.OK, "OK", List.of());
//    }
    @GetMapping
    public ResponseEntity getSaleOrders(SaleOrderRequest request) {
        var result = saleOrderService.getSaleOrders(request);
        if (result == null || result.size() == 0) {
            return ApiResponseHandler.RespondError(HttpStatus.NOT_FOUND, "not found any matched orders","SALE_ORDER_NOT_FOUND");
        }
        return ApiResponseHandler.Respond(HttpStatus.OK,"query sale orders successfully", result);
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
