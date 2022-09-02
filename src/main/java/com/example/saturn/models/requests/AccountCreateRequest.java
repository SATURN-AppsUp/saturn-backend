package com.example.saturn.models.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class AccountCreateRequest {
    @NotEmpty(message = "username can not be null")
    private String username;
    @NotEmpty(message = "pass can not be null")
    private String password;
    @NotEmpty(message = "name can not be null")
    private String name;
    private LocalDate dob;
    @NotEmpty
    private String phone;
    @NotEmpty(message = "email can not be null")
    private String email;
    @NotEmpty
    private boolean gender;
    private boolean isSeller;

}
