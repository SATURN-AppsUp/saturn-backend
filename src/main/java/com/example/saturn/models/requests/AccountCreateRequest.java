package com.example.saturn.models.requests;

import com.example.saturn.models.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
public class AccountCreateRequest {
    @NotEmpty(message = "username can not be null")
    @Pattern(regexp = "^[a-zA-Z0-9_]*$", message = "username can only contain characters.")
    private String username;
    @NotEmpty(message = "password can not be null")
    @Min(8)
    private String password;
    @NotEmpty(message = "name can not be null")
    private String name;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate dob;
    @NotEmpty(message = "phone cannot be null")
    private String phone;
    @NotEmpty(message = "email can not be null")
    private String email;
    @NotNull(message = "gender can't be null")
    private Gender gender;
    private boolean isSeller;

}
