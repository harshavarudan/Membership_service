package com.example.membership_service.model;

import com.example.membership_service.model.enums.MembershipPlan;
import com.example.membership_service.model.enums.MembershipTier;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "subscriptions")
public class Subscription {
    @GeneratedValue
    @Id
    private Long id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private MembershipPlan membershipPlan;
    @ManyToOne
    private User user;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @NotNull
    @Enumerated(EnumType.STRING)
    private MembershipTier membershipTier;
    private boolean active = true;
}
