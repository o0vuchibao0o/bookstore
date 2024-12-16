package com.vuchibao.bookstore.order.domain.dto.request;

import com.vuchibao.bookstore.order.domain.dto.model.Address;
import com.vuchibao.bookstore.order.domain.dto.model.Customer;
import com.vuchibao.bookstore.order.domain.dto.model.OrderItem;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.Set;

public record CreateOrderRequest(
        @NotEmpty(message = "Items cannot be empty") Set<OrderItem> items,
        @Valid Customer customer,
        @Valid Address deliveryAddress) {}
