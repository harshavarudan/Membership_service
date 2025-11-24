package com.example.membership_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class OrderRequest {
    @NotNull
    private Long userId;
    @NotNull
    @Positive
    private double cost;
}
