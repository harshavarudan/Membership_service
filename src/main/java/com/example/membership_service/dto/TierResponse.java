package com.example.membership_service.dto;

import com.example.membership_service.model.enums.MembershipTier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TierResponse {
    private MembershipTier tier;
    private String name;
}
