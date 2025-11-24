package com.example.membership_service.model.enums;

public enum MembershipTier {
    Gold("Gold"),Silver("Silver"),Platinum("Platinum"),EXPIRED("EXPIRED")
    ;

    private String name;

    MembershipTier(String name) {
        this.name = name;
    }
}
