package com.example.saturn.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private int status;
    private String message;
    private List<T> data;
}
