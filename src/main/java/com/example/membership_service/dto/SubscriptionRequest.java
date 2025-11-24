package com.example.membership_service.dto;

import com.example.membership_service.model.enums.MembershipPlan;
import com.example.membership_service.model.enums.MembershipTier;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SubscriptionRequest {
    @NotNull
    private Long userId;
    @NotNull
    private MembershipPlan membershipPlan;
    @NotNull
    private MembershipTier membershipTier;
}
