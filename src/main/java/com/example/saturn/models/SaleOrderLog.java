package com.example.saturn.models;

import com.example.saturn.models.enums.SaleOrderStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Data
public class SaleOrderLog {
    private SaleOrderStatus status;
    private int updatedBy;
    private LocalDateTime actionTime;
    private Map<String, Object> extraData;

    public SaleOrderLog(SaleOrderStatus status, int updatedBy, LocalDateTime actionTime) {
        this.status = status;
        this.updatedBy = updatedBy;
        this.actionTime = actionTime;
    }
}
