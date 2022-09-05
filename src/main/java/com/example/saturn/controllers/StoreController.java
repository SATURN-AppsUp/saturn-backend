package com.example.saturn.controllers;


import com.example.saturn.models.Store;
import com.example.saturn.models.requests.StoreCreateRequest;
import com.example.saturn.models.requests.StoreRequest;
import com.example.saturn.services.StoreService;
import com.example.saturn.utils.ApiResponseHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/store")
@AllArgsConstructor
public class StoreController {

    public final StoreService storeService;

    @GetMapping
    public ResponseEntity<Store> GetStore(StoreRequest request) {
        var data = storeService.getStore(request);

        if (data.size() > 0) {
            return ApiResponseHandler.Respond(HttpStatus.OK, "query store successfully", data);
        }
        else
            return ApiResponseHandler.Respond(HttpStatus.NOT_FOUND, "not found any matched store", List.of());
    }

    @PostMapping
    public ResponseEntity<Store> createStore(@RequestBody StoreCreateRequest request) {
        var createdStore = storeService.createStore(request);
        if (createdStore.size() > 0) {
            return ApiResponseHandler.Respond(HttpStatus.OK, "created store successfully", createdStore);
        }
        else
            return ApiResponseHandler.Respond(HttpStatus.INTERNAL_SERVER_ERROR, "couldn't create store", List.of());
    }
}
