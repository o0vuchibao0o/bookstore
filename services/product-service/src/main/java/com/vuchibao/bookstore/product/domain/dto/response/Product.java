package com.vuchibao.bookstore.product.domain.dto.response;

import java.math.BigDecimal;

public record Product(String code, String name, String description, String imageUrl, BigDecimal price) {}