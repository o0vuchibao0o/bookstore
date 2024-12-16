package com.vuchibao.bookstore.order.domain.repository;

import com.vuchibao.bookstore.order.domain.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {}
