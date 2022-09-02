package com.example.saturn.models;

import com.example.saturn.models.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Document
public class Account {
    @Id
    private int id;
    @NotEmpty(message = "username can not be null")
    private String username;
    @NotEmpty(message = "pass can not be null")
    private String password;
    @NotEmpty(message = "name can not be null")
    private String name;
    private LocalDate dob;
    @NotEmpty(message = "phone can not be null")
    private String phone;
    @Indexed
    @NotEmpty(message = "email can not be null")
    private String email;
    private AccountStatus status;
    @NotEmpty
    private boolean gender;
    private boolean isSeller;
    private int deliveredOrders;
    private int processingOrders;
    private int waitToConfirmedOrders;
    private int points;


}
