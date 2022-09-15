package com.example.saturn.models;

import com.example.saturn.models.enums.AccountStatus;
import com.example.saturn.models.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
public class Account {
    @Id
    private int id;
    private String username;
    private String password;
    private String name;
    private LocalDate dob;
    private String phone;
    @Indexed
    private String email;
    private AccountStatus status;
    private Gender gender;
    private boolean isSeller;
    private int deliveredOrders;
    private int processingOrders;
    private int waitToConfirmedOrders;
    private int points;
    private List<Address> addressList;
}
