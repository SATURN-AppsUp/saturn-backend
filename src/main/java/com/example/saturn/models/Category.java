package com.example.saturn.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class Category {
    @Id
    private int id;
    private String categoryCode;
    private String categoryName;
}
