package com.example.membership_service.service;

import com.example.membership_service.dto.SubscriptionResponse;
import com.example.membership_service.exception.SubscriptionAlreadyExistsException;
import com.example.membership_service.exception.SubscriptionNotFoundException;
import com.example.membership_service.exception.UserNotFoundException;
import com.example.membership_service.model.Subscription;
import com.example.membership_service.model.User;
import com.example.membership_service.model.enums.MembershipPlan;
import com.example.membership_service.model.enums.MembershipTier;
import com.example.membership_service.repo.SubscriptionRepository;
import com.example.membership_service.repo.UserRepository;
import com.example.membership_service.service.tier.TierEvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final TierEvaluationService tierEvaluationService;

    @Transactional
    public SubscriptionResponse createSubscription(Long userId, MembershipPlan membershipPlan, MembershipTier membershipTier) {
        User user = getUserOrThrow(userId);

        Subscription currentSub = getCurrentSubscription(user);
        if (currentSub != null) {
            throw new SubscriptionAlreadyExistsException("User already has an active subscription");
        }

        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setStartDate(LocalDateTime.now());
        subscription.setEndDate(LocalDateTime.now().plusDays(membershipPlan.getDays()));
        subscription.setMembershipPlan(membershipPlan);
        subscription.setMembershipTier(membershipTier);
        subscription.setActive(true);

        Subscription saved = subscriptionRepository.save(subscription);
        return mapToResponse(saved);
    }

    public Subscription getCurrentSubscription(User user) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        Optional<Subscription> currentSubscription = user.getSubscriptions().stream()
                .filter(x -> x.getEndDate().isAfter(currentDateTime) && x.isActive())
                .findFirst();
        return currentSubscription.orElse(null);
    }

    public SubscriptionResponse getCurrentSubscriptionByUserId(Long userId) {
        User user = getUserOrThrow(userId);
        Subscription subscription = getCurrentSubscription(user);
        return subscription != null ? mapToResponse(subscription) : null;
    }

    @Transactional
    public SubscriptionResponse upgradeTier(Long userId, MembershipTier newTier) {
        User user = getUserOrThrow(userId);

        Subscription currentSub = getCurrentSubscription(user);
        if (currentSub == null) {
            throw new SubscriptionNotFoundException("No active subscription found");
        }

        currentSub.setMembershipTier(newTier);
        Subscription updated = subscriptionRepository.save(currentSub);
        return mapToResponse(updated);
    }

    @Transactional
    public void cancelSubscription(Long userId) {
        User user = getUserOrThrow(userId);

        Subscription currentSub = getCurrentSubscription(user);
        if (currentSub == null) {
            throw new SubscriptionNotFoundException("No active subscription found");
        }

        currentSub.setActive(false);
        currentSub.setEndDate(LocalDateTime.now());
        subscriptionRepository.save(currentSub);
    }

    @Transactional
    public SubscriptionResponse autoUpgradeTier(Long userId, String criteriaType) {
        User user = getUserOrThrow(userId);

        Subscription currentSub = getCurrentSubscription(user);
        if (currentSub == null) {
            throw new SubscriptionNotFoundException("No active subscription found");
        }

        MembershipTier evaluatedTier = tierEvaluationService.evaluateTier(user, criteriaType);
        currentSub.setMembershipTier(evaluatedTier);
        Subscription updated = subscriptionRepository.save(currentSub);
        return mapToResponse(updated);
    }

    private User getUserOrThrow(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    private SubscriptionResponse mapToResponse(Subscription subscription) {
        return SubscriptionResponse.builder()
                .subscriptionId(subscription.getId())
                .userId(subscription.getUser().getUserId())
                .membershipPlan(subscription.getMembershipPlan())
                .membershipTier(subscription.getMembershipTier())
                .startDate(subscription.getStartDate())
                .endDate(subscription.getEndDate())
                .active(subscription.isActive())
                .build();
    }
}
