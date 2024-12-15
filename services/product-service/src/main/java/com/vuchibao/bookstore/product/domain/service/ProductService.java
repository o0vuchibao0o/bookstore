package com.vuchibao.bookstore.product.domain.service;

import com.vuchibao.bookstore.product.ApplicationProperties;
import com.vuchibao.bookstore.product.domain.dto.response.PagedResult;
import com.vuchibao.bookstore.product.domain.dto.response.Product;
import com.vuchibao.bookstore.product.domain.mapper.ProductMapper;
import com.vuchibao.bookstore.product.domain.repository.ProductRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ApplicationProperties properties;

    public PagedResult<Product> getProducts(int pageNo) {
        Sort sort = Sort.by("name").ascending();
        pageNo = pageNo <= 1 ? 0 : pageNo - 1;
        Pageable pageable = PageRequest.of(pageNo, properties.pageSize(), sort);
        Page<Product> productsPage = productRepository.findAll(pageable).map(ProductMapper::toProduct);

        return new PagedResult<>(
                productsPage.getContent(),
                productsPage.getTotalElements(),
                productsPage.getNumber() + 1,
                productsPage.getTotalPages(),
                productsPage.isFirst(),
                productsPage.isLast(),
                productsPage.hasPrevious(),
                productsPage.hasNext());
    }

    public Optional<Product> getProductByCode(String code) {
        return productRepository.findByCode(code).map(ProductMapper::toProduct);
    }
}
