package com.example.saturn.models.responses;

import com.example.saturn.models.enums.AccountStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

public class AccountProfileRepsonse {
    @Id
    private int id;
    private String username;
    private String name;
    private LocalDate dob;
    private String phone;
    private String email;
    private AccountStatus status;
    private boolean gender;
    private boolean isSeller;
    private int deliveredOrders;
    private int processingOrders;
    private int waitToConfirmedOrders;
    private int points;
}
