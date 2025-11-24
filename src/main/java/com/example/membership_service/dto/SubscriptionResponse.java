package com.example.membership_service.dto;

import com.example.membership_service.model.enums.MembershipPlan;
import com.example.membership_service.model.enums.MembershipTier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionResponse {
    private Long subscriptionId;
    private Long userId;
    private MembershipPlan membershipPlan;
    private MembershipTier membershipTier;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean active;
}
