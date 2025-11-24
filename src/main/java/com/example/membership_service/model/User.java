package com.example.membership_service.model;

import com.example.membership_service.model.enums.MembershipPlan;
import com.example.membership_service.model.enums.MembershipTier;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @GeneratedValue
    @Id
    private Long userId;
    @NotNull
    private String name;
    @Email
    @NotNull
    @Column(unique = true)
    private String email;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Subscription> subscriptions;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Order> orders;
}
