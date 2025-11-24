package com.example.membership_service.dto;

import com.example.membership_service.model.enums.MembershipPlan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanResponse {
    private MembershipPlan plan;
    private String name;
    private int durationDays;
    private double price;
}
