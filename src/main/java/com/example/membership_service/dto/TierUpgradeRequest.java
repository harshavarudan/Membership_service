package com.example.membership_service.dto;

import com.example.membership_service.model.enums.MembershipTier;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TierUpgradeRequest {
    @NotNull
    private Long userId;
    @NotNull
    private MembershipTier newTier;
}
