package com.vuchibao.bookstore.product;

import org.springframework.boot.SpringApplication;

class TestProductServiceApplication {
    public static void main(String[] args) {
        SpringApplication.from(ProductServiceApplication::main)
                .with(TestcontainersConfiguration.class)
                .run(args);
    }
}
