package com.example.saturn.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private HttpStatus result;
    private String message;
    private List<T> data;
}
