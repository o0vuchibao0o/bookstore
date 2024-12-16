package com.vuchibao.bookstore.order.domain.mapper;

import com.vuchibao.bookstore.order.domain.dto.model.OrderItem;
import com.vuchibao.bookstore.order.domain.dto.model.OrderStatus;
import com.vuchibao.bookstore.order.domain.dto.request.CreateOrderRequest;
import com.vuchibao.bookstore.order.domain.entity.OrderEntity;
import com.vuchibao.bookstore.order.domain.entity.OrderItemEntity;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class OrderMapper {
    public static OrderEntity toOrderEntity(CreateOrderRequest request) {
        OrderEntity newOrder = new OrderEntity();
        newOrder.setOrderNumber(UUID.randomUUID().toString());
        newOrder.setStatus(OrderStatus.NEW);
        newOrder.setCustomer(request.customer());
        newOrder.setDeliveryAddress(request.deliveryAddress());

        Set<OrderItemEntity> orderItems = new HashSet<>();

        for (OrderItem item : request.items()) {
            OrderItemEntity orderItem = new OrderItemEntity();
            orderItem.setCode(item.code());
            orderItem.setName(item.name());
            orderItem.setPrice(item.price());
            orderItem.setQuantity(item.quantity());
            orderItem.setOrder(newOrder);

            orderItems.add(orderItem);
        }
        newOrder.setItems(orderItems);
        return newOrder;
    }
}
