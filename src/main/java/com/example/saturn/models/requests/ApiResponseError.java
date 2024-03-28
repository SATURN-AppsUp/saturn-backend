package com.example.saturn.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
public class ApiResponseError {
    private HttpStatus status;
    private String message;
    private String errorCode;
}
