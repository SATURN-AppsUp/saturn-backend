package com.example.saturn.models.requests;

import com.example.saturn.models.enums.AccountStatus;
import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.Null;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class AccountRequest {
    private String username;
    @Nullable
    private String name;
    @Nullable
    private LocalDate dob;
    @Nullable
    private String phone;
    @Nullable
    private String email;
    @Nullable
    private AccountStatus status;
    @Nullable
    private boolean gender;
    @Nullable
    private boolean isSeller;
    @Nullable
    private int deliveredOrders;
    @Nullable
    private int processingOrders;
    @Nullable
    private int waitToConfirmedOrders;
    @Nullable
    private int points;
}
