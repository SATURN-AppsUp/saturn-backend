package com.example.saturn.models;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class Address {
    @NotEmpty
    private int userId;
    @NotEmpty
    private String street;
    @NotEmpty
    private String ward;
    @NotEmpty
    private String district;
    @NotEmpty
    private String city;
    @NotNull
    private String province;
}
