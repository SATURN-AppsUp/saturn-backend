package com.example.saturn.controllers;

import com.example.saturn.models.Account;
import com.example.saturn.models.requests.AccountCreateRequest;
import com.example.saturn.models.requests.ApiResponse;
import com.example.saturn.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/account")
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    private ApiResponse<Account> getAllAccounts() {
        return new ApiResponse(HttpStatus.OK.value(),"query account successfully",accountService.getAllAccounts());
    }

    @GetMapping("/profile")
    private ApiResponse<Account> getProfile(@RequestBody Account account) {
        var data = accountService.getProfile(account);
        if (data.size() > 0 ) {
            return new ApiResponse<>(HttpStatus.OK.value(), "query profile successfully", data);
        }
        else {
            return new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "not found any matched profile", data);
        }
    }

    @PostMapping
    private ApiResponse<Account> createAccount(@Valid @RequestBody AccountCreateRequest request) {
        List data = new ArrayList<Account>();
        try {
            var result = accountService.createAccount(request);
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
