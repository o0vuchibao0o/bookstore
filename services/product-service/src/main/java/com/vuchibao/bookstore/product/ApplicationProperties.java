package com.vuchibao.bookstore.product;

import jakarta.validation.constraints.Min;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties(prefix = "product")
public record ApplicationProperties(@DefaultValue("10") @Min(1) int pageSize) {}
