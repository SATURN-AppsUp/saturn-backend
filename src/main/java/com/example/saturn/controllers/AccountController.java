package com.example.saturn.controllers;

import com.example.saturn.models.Account;
import com.example.saturn.models.requests.AccountCreateRequest;
import com.example.saturn.models.requests.AccountRequest;
import com.example.saturn.services.AccountService;
import com.example.saturn.utils.ApiResponseHandler;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/account")
public class AccountController {
    private final AccountService accountService;
    private final RabbitTemplate template;
    @GetMapping
    private ResponseEntity getAccount(AccountRequest request) {
        var accounts  = accountService.getAccounts(request);
        if (accounts.size() > 0 )
            return  ApiResponseHandler.Respond(
                HttpStatus.OK,
                "query account successfully",
                accounts);
        else return  ApiResponseHandler.RespondError(HttpStatus.NOT_FOUND, "not found any matched accounts","ACCOUNT_NOT_FOUND");

    }

    @GetMapping("/test")
    private ResponseEntity healthCheck() {
        return ApiResponseHandler.Respond(HttpStatus.OK,"OK",List.of());
    }

    @GetMapping("/profile")
    private ResponseEntity getProfile(AccountRequest account) {
        System.out.println(account);
        var data = accountService.getProfile(account);
        if (data.size() > 0 ) {
            return ApiResponseHandler.Respond(HttpStatus.OK, "query profile successfully", data);
        }
        else {
            return ApiResponseHandler.Respond(HttpStatus.NOT_FOUND, "not found any matched profile", data);
        }
    }

    @PostMapping
    private ResponseEntity createAccount(@Valid @RequestBody AccountCreateRequest request) {
        List data = new ArrayList<Account>();
        template.convertAndSend("","q.account-registration", request);
            var result = accountService.createAccount(request);
            if (result.getId() > 0) {
                data.add(result);
                return ApiResponseHandler.Respond(HttpStatus.OK,"Account created successfully", data);
            } else {
                return ApiResponseHandler.RespondError(HttpStatus.INTERNAL_SERVER_ERROR, "Account created fail", "ACCOUNT_CREATE_ERROR");
            }
    }
}
