package com.vuchibao.bookstore.product.web.controller;

import com.vuchibao.bookstore.product.domain.dto.response.PagedResult;
import com.vuchibao.bookstore.product.domain.dto.response.Product;
import com.vuchibao.bookstore.product.domain.exception.ProductNotFoundException;
import com.vuchibao.bookstore.product.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
class ProductController {
    private final ProductService productService;

    @GetMapping
    PagedResult<Product> getProducts(@RequestParam(name = "page", defaultValue = "1") int pageNo) {
        return productService.getProducts(pageNo);
    }

    @GetMapping("/{code}")
    ResponseEntity<Product> getProductByCode(@PathVariable String code) {
        return productService
                .getProductByCode(code)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> ProductNotFoundException.forCode(code));
    }
}
