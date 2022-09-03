package com.example.saturn.models;

import com.example.saturn.models.enums.AccountStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Account {
    @Id
    private int id;
    private String username;
    @JsonIgnore
    private String password;
    private String name;
    private LocalDate dob;
    private String phone;
    @Indexed
    private String email;
    private AccountStatus status;
    private boolean gender;
    private boolean isSeller;
    private int deliveredOrders;
    private int processingOrders;
    private int waitToConfirmedOrders;
    private int points;


}
