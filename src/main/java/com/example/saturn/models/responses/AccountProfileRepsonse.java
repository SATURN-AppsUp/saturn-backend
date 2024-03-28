package com.example.saturn.models.responses;

import com.example.saturn.models.Address;
import com.example.saturn.models.enums.AccountStatus;
import com.example.saturn.models.enums.Gender;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class AccountProfileRepsonse {
    @Id
    private int id;
    private String username;
    private String name;
    private LocalDateTime dob;
    private String phone;
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
