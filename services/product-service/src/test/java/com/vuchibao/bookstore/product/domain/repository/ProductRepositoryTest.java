package com.vuchibao.bookstore.product.domain.repository;

import com.vuchibao.bookstore.product.domain.entity.ProductEntity;
import java.math.BigDecimal;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest(
        properties = {
            "spring.test.database.replace=none",
            "spring.datasource.url=jdbc:tc:postgresql:17.2-bookworm:///db"
        })
// @Import(TestcontainersConfiguration.class)
@Sql("/test-data.sql")
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldGetAllProducts() {
        List<ProductEntity> products = productRepository.findAll();
        Assertions.assertThat(products).hasSize(15);
    }

    @Test
    void shouldGetProductByCode() {
        ProductEntity product = productRepository.findByCode("P100").orElseThrow();
        Assertions.assertThat(product.getCode()).isEqualTo("P100");
        Assertions.assertThat(product.getName()).isEqualTo("The Hunger Games");
        Assertions.assertThat(product.getDescription())
                .isEqualTo("Winning will make you famous. Losing means certain death...");
        Assertions.assertThat(product.getImageUrl())
                .isEqualTo("https://images.gr-assets.com/books/1447303603l/2767052.jpg");
        Assertions.assertThat(product.getPrice()).isEqualTo(new BigDecimal("34.0"));
    }

    @Test
    void shouldReturnEmptyWhenProductCodeNotExists() {
        String code = "invalid_product_code";
        Assertions.assertThat(productRepository.findByCode(code)).isEmpty();
    }
}
