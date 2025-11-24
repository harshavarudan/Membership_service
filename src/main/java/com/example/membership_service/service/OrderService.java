package com.example.membership_service.service;

import com.example.membership_service.exception.UserNotFoundException;
import com.example.membership_service.model.Order;
import com.example.membership_service.model.User;
import com.example.membership_service.repo.OrderRepository;
import com.example.membership_service.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Transactional
    public Order createOrder(Long userId, double cost) {
        User user = getUserOrThrow(userId);

        Order order = new Order();
        order.setUser(user);
        order.setCost(cost);
        order.setOrderTimeStamp(LocalDateTime.now());

        return orderRepository.save(order);
    }

    public List<Order> getUserOrders(Long userId) {
        User user = getUserOrThrow(userId);
        return user.getOrders();
    }

    private User getUserOrThrow(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }
}
