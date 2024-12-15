package com.vuchibao.bookstore.product.domain.mapper;

import com.vuchibao.bookstore.product.domain.dto.response.Product;
import com.vuchibao.bookstore.product.domain.entity.ProductEntity;

public class ProductMapper {
    public static Product toProduct(ProductEntity productEntity) {
        return new Product(
                productEntity.getCode(),
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getImageUrl(),
                productEntity.getPrice());
    }
}
