package com.example.saturn.controllers;

import com.example.saturn.models.Account;
import com.example.saturn.models.Seller;
import com.example.saturn.models.requests.ApiResponse;
import com.example.saturn.models.requests.SellerCreateRequest;
import com.example.saturn.services.SellerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
    private ApiResponse<Seller> getSeller(@RequestBody Seller seller) {
        List data = new ArrayList();
        var result = sellerService.getSeller(seller);
        if (result.size() > 0) {
            return new ApiResponse(HttpStatus.OK.value(), "Query seller successfully", result);
        }
        else {
            return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "Not found any matched seller", List.of());
        }
    }

    @PostMapping
    private ApiResponse<Account> createSeller(@Valid @RequestBody SellerCreateRequest request) {
        List data = new ArrayList<Account>();
        try {
            var result = sellerService.createSeller(request);
            if (result.getId() > 0) {
                data.add(result);
                return new ApiResponse<Account>(HttpStatus.CREATED.value(), "Account created successfully", data);
            } else {
                return new ApiResponse<Account>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Account created fail", data);
            }
        } catch (Exception e) {
            var errorMessage = e.getMessage().length() > 0 ? e.getMessage() : "Account created fail";
            return new ApiResponse<Account>(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage, data);
        }
    }
}
