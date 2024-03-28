package com.example.saturn.models.enums;

import lombok.Data;

import java.util.List;

@Data
public class RentInformation {
    private int minimumRentDays;
    private int maximumRentDay;
    private int minimumDeposit;
    private int rentPricePerDay;
    private List<String> productReturnConditions;

    public boolean isValid() {
        return minimumDeposit >= 0 && minimumRentDays >= 0 && minimumDeposit >= 0 && productReturnConditions.size() > 0;
    }
}
