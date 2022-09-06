package com.example.saturn.controllers;

import com.example.saturn.models.Account;
import com.example.saturn.models.Seller;
import com.example.saturn.models.requests.ApiResponse;
import com.example.saturn.models.requests.SellerCreateRequest;
import com.example.saturn.models.requests.SellerRequest;
import com.example.saturn.models.requests.SellerUpdateRequest;
import com.example.saturn.services.SellerService;
import com.example.saturn.utils.ApiResponseHandler;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/seller")
public class SellerController {


    private final SellerService sellerService;

    @GetMapping
    private ResponseEntity<Seller> getSeller(SellerRequest seller) {
        var result = sellerService.getSeller(seller);
        if (result.size() > 0) {
            return ApiResponseHandler.Respond(HttpStatus.OK, "Query seller successfully", result);
        }
        else {
            return ApiResponseHandler.Respond(HttpStatus.NOT_FOUND, "Not found any matched seller", List.of());
        }
    }

    @PutMapping
    private ResponseEntity<Seller> updateSeller(@RequestBody SellerUpdateRequest request) {
        var result = sellerService.updateSeller(request);
        if (result.size() > 0) {
            return ApiResponseHandler.Respond(HttpStatus.OK, "updated seller successfully", result);
        } return ApiResponseHandler.Respond(HttpStatus.NOT_FOUND, "not found any seller", List.of());
    }
    @PostMapping
    private ResponseEntity<Seller> createSeller(@Valid @RequestBody SellerCreateRequest request) {
        List data = new ArrayList<Account>();
        try {
            var result = sellerService.createSeller(request);
            if (result.getId() > 0) {
                data.add(result);
                return ApiResponseHandler.Respond(HttpStatus.OK, "Seller created successfully", data);
            } else {
                return ApiResponseHandler.Respond(HttpStatus.INTERNAL_SERVER_ERROR, "Seller created fail", data);
            }
        } catch (Exception e) {
            var errorMessage = e.getMessage().length() > 0 ? e.getMessage() : "Seller created fail";
            return ApiResponseHandler.Respond(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage, data);
        }
    }
}
