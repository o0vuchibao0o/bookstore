package com.vuchibao.bookstore.order.web.controller;

import com.vuchibao.bookstore.order.domain.dto.request.CreateOrderRequest;
import com.vuchibao.bookstore.order.domain.dto.response.CreateOrderResponse;
import com.vuchibao.bookstore.order.domain.service.OrderService;
import com.vuchibao.bookstore.order.domain.service.SecurityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
class OrderController {
    private final OrderService orderService;
    private final SecurityService securityService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CreateOrderResponse createOrder(@Valid @RequestBody CreateOrderRequest request) {
        String username = securityService.getLoginUsername();
        log.info("Creating order for user {}", username);
        return orderService.createOrder(username, request);
    }
}
