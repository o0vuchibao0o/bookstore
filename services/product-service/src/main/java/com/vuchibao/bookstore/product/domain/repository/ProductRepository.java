package com.vuchibao.bookstore.product.domain.repository;

import com.vuchibao.bookstore.product.domain.entity.ProductEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findByCode(String code);
}
