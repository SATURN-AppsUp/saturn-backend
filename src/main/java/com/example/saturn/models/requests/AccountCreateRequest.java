package com.example.saturn.models.requests;

import com.example.saturn.models.Address;
import com.example.saturn.models.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mongodb.lang.Nullable;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class AccountCreateRequest implements Serializable {
    @NotEmpty(message = "username can not be null")
    @Pattern(regexp = "^[a-zA-Z0-9_]*$", message = "username can only contain characters.")
    private String username;
    @NotEmpty(message = "password can not be null")
    private String password;
    @NotEmpty(message = "name can not be null")
    private String name;
    private LocalDateTime dob;
    @NotEmpty(message = "phone cannot be null")
    private String phone;
    @NotEmpty(message = "email can not be null")
    private String email;
    @NotNull(message = "gender can't be null")
    private Gender gender;
    @Nullable
    private List<Address> addressList;
    private boolean isSeller;

}
