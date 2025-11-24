package com.example.membership_service.controller;

import com.example.membership_service.dto.PlanResponse;
import com.example.membership_service.dto.TierResponse;
import com.example.membership_service.service.MembershipPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/membership")
@RequiredArgsConstructor
public class MembershipController {

    private final MembershipPlanService membershipPlanService;

    @GetMapping("/plans")
    public ResponseEntity<List<PlanResponse>> getAllPlans() {
        return ResponseEntity.ok(membershipPlanService.getAllPlans());
    }

    @GetMapping("/tiers")
    public ResponseEntity<List<TierResponse>> getAllTiers() {
        return ResponseEntity.ok(membershipPlanService.getAllTiers());
    }
}
