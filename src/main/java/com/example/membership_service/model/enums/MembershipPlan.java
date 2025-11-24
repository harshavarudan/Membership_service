package com.example.membership_service.model.enums;

public enum MembershipPlan {
    Monthly("Monthly",30),
    Quarterly("Quarterly",90),
    Yearly("Yearly",365)
    ;
    private String name;
    private int days;

    MembershipPlan(String name,int days) {
        this.name = name;
        this.days=days;
    }

    public String getName() {
        return name;
    }

    public int getDays() {
        return days;
    }

}
