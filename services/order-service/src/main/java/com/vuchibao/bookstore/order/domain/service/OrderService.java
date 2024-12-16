package com.vuchibao.bookstore.order.domain.service;

import com.vuchibao.bookstore.order.domain.dto.request.CreateOrderRequest;
import com.vuchibao.bookstore.order.domain.dto.response.CreateOrderResponse;
import com.vuchibao.bookstore.order.domain.entity.OrderEntity;
import com.vuchibao.bookstore.order.domain.mapper.OrderMapper;
import com.vuchibao.bookstore.order.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public CreateOrderResponse createOrder(String username, CreateOrderRequest request) {
        OrderEntity newOrder = OrderMapper.toOrderEntity(request);
        newOrder.setUsername(username);
        OrderEntity savedOrder = orderRepository.save(newOrder);
        log.info("Created order with orderNumber = {}", savedOrder.getOrderNumber());
        return new CreateOrderResponse(savedOrder.getOrderNumber());
    }
}
