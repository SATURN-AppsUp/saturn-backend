package com.example.saturn.controllers;

import com.example.saturn.models.SKU;
import com.example.saturn.models.requests.ApiResponse;
import com.example.saturn.models.requests.SKUCreateRequest;
import com.example.saturn.models.requests.SKURequest;
import com.example.saturn.services.SKUService;
import com.example.saturn.utils.ApiResponseHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("/api/sku")
@AllArgsConstructor
public class SKUController {

    private final SKUService skuService;

//    @GetMapping("/sku-options")
//    public ResponseEntity getSKUOptions() {
//
//    }
    @GetMapping
    public ResponseEntity getSKU(SKURequest skuRequest) {
        var result = skuService.getSKUs(skuRequest);
        if (result.isEmpty()) {
            return ApiResponseHandler.RespondError(HttpStatus.BAD_REQUEST,"not found any sku","SKU_NOT_FOUND");
        }
        return ApiResponseHandler.Respond(HttpStatus.OK,"query sku successfully", List.of(result));
    }

    @PostMapping
    public ResponseEntity createSKU(@Valid @RequestBody SKUCreateRequest skuCreateRequest) {
        var createSKU = skuService.createSKU(skuCreateRequest);
        if (createSKU != null) {
            return ApiResponseHandler.Respond(HttpStatus.OK,"sku created successfully", List.of(createSKU));
        }
        else return ApiResponseHandler.RespondError(HttpStatus.INTERNAL_SERVER_ERROR,"sku created failed","SKU_CREATE_ERROR");
    }

//    @GetMapping
//    public ResponseEntity getSKU(SKURequest request) {
//        List<SKU> result = skuService.getSKUs(request);
//    }
}
