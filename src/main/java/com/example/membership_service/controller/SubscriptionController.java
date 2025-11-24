package com.example.membership_service.controller;

import com.example.membership_service.dto.SubscriptionRequest;
import com.example.membership_service.dto.SubscriptionResponse;
import com.example.membership_service.dto.TierUpgradeRequest;
import com.example.membership_service.service.SubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<SubscriptionResponse> createSubscription(@Valid @RequestBody SubscriptionRequest request) {
        SubscriptionResponse response = subscriptionService.createSubscription(
                request.getUserId(),
                request.getMembershipPlan(),
                request.getMembershipTier()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<SubscriptionResponse> getCurrentSubscription(@PathVariable Long userId) {
        SubscriptionResponse response = subscriptionService.getCurrentSubscriptionByUserId(userId);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/upgrade")
    public ResponseEntity<SubscriptionResponse> upgradeTier(@Valid @RequestBody TierUpgradeRequest request) {
        SubscriptionResponse response = subscriptionService.upgradeTier(request.getUserId(), request.getNewTier());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/auto-upgrade/{userId}")
    public ResponseEntity<SubscriptionResponse> autoUpgradeTier(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "ORDER_COUNT") String criteriaType) {
        SubscriptionResponse response = subscriptionService.autoUpgradeTier(userId, criteriaType);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> cancelSubscription(@PathVariable Long userId) {
        subscriptionService.cancelSubscription(userId);
        return ResponseEntity.noContent().build();
    }
}
