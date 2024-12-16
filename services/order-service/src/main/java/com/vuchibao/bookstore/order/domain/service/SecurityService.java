package com.vuchibao.bookstore.order.domain.service;

import org.springframework.stereotype.Service;

@Service
public class SecurityService {
    public String getLoginUsername() {
        return "user";
    }
}
