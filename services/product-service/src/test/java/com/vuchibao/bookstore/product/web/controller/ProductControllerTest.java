package com.vuchibao.bookstore.product.web.controller;

import com.vuchibao.bookstore.product.AbstractIT;
import com.vuchibao.bookstore.product.domain.dto.response.Product;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.text.StringSubstitutor;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

@Sql("/test-data.sql")
class ProductControllerTest extends AbstractIT {

    @Test
    void shouldReturnProducts() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/products")
                .then()
                .statusCode(200)
                .body("data", Matchers.hasSize(10))
                .body("totalElements", Matchers.is(15))
                .body("pageNumber", Matchers.is(1))
                .body("totalPages", Matchers.is(2))
                .body("isFirst", Matchers.is(true))
                .body("isLast", Matchers.is(false))
                .body("hasPrevious", Matchers.is(false))
                .body("hasNext", Matchers.is(true));
    }

    @Test
    void shouldGetProductByCode() {
        String code = "P100";
        Product product = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/products/{code}", code)
                .then()
                .statusCode(200)
                .assertThat()
                .extract()
                .body()
                .as(Product.class);

        Assertions.assertThat(product.code()).isEqualTo("P100");
        Assertions.assertThat(product.name()).isEqualTo("The Hunger Games");
        Assertions.assertThat(product.description())
                .isEqualTo("Winning will make you famous. Losing means certain death...");
        Assertions.assertThat(product.imageUrl())
                .isEqualTo("https://images.gr-assets.com/books/1447303603l/2767052.jpg");
        Assertions.assertThat(product.price()).isEqualTo(new BigDecimal("34.0"));
    }

    @Test
    void shouldReturnNotFoundWhenProductCodeNotExists() {
        String code = "invalid_product_code";
        Map<String, String> values = new HashMap<>();
        values.put("code", code);

        String template = "Product with code ${code} not found";
        String message = new StringSubstitutor(values).replace(template);
        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/products/{code}", code)
                .then()
                .statusCode(404)
                .body("status", Matchers.is(404))
                .body("title", Matchers.is("Product Not Found"))
                .body("detail", Matchers.is(message));
    }
}
