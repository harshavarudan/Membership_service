package com.example.membership_service.service;

import com.example.membership_service.dto.PlanResponse;
import com.example.membership_service.dto.TierResponse;
import com.example.membership_service.model.enums.MembershipPlan;
import com.example.membership_service.model.enums.MembershipTier;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MembershipPlanService {

    public List<PlanResponse> getAllPlans() {
        return Arrays.stream(MembershipPlan.values())
                .map(plan -> PlanResponse.builder()
                        .plan(plan)
                        .name(plan.getName())
                        .durationDays(plan.getDays())
                        .price(getPlanPrice(plan))
                        .build())
                .collect(Collectors.toList());
    }

    public List<TierResponse> getAllTiers() {
        return Arrays.stream(MembershipTier.values())
                .filter(tier -> tier != MembershipTier.EXPIRED)
                .map(tier -> TierResponse.builder()
                        .tier(tier)
                        .name(tier.name())
                        .build())
                .collect(Collectors.toList());
    }

    private double getPlanPrice(MembershipPlan plan) {
        return switch (plan) {
            case Monthly -> 299.0;
            case Quarterly -> 799.0;
            case Yearly -> 2999.0;
        };
    }
}
