package com.example.saturn.models.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private int total;

    public ApiResponse(HttpStatus result, String message, List<T> data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }
}
