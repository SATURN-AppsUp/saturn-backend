package com.example.saturn.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ApiResponseError {
    private String message;
    private String errorCode;
}
