package com.example.membership_service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @GeneratedValue
    @Id
    private Long id;
    @ManyToOne
    @JsonIgnoreProperties({"subscriptions", "orders"})
    private User user;
    private double cost;
    private LocalDateTime orderTimeStamp;
}
