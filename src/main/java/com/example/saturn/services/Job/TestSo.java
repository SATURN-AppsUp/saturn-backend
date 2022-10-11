package com.example.saturn.services.Job;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "test_so")
@Data
@AllArgsConstructor
public class TestSo {
    private String saleOrderCode;
}