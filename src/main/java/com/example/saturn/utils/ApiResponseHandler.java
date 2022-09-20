package com.example.saturn.utils;

import com.example.saturn.models.requests.ApiResponse;
import com.example.saturn.models.requests.ApiResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public final class ApiResponseHandler {


    public static ResponseEntity Respond(HttpStatus status, String message, List data, int total) {
        return new ResponseEntity<ApiResponse>(new ApiResponse<>(status,message, data, total),status);
    }

    public static ResponseEntity Respond(HttpStatus status, String message, List data) {

        return new ResponseEntity<ApiResponse>(new ApiResponse<>(status,message, data),status);
    }


    public static ResponseEntity RespondError(HttpStatus status, String message, String errorCode) {
        return new ResponseEntity(new ApiResponseError(status, message, errorCode), status);
    }
}
