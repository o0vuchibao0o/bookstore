package com.vuchibao.bookstore.product.domain.dto.response;

import java.util.List;

public record PagedResult<T>(
        List<T> data,
        long totalElements,
        int pageNumber,
        int totalPages,
        boolean isFirst,
        boolean isLast,
        boolean hasPrevious,
        boolean hasNext) {}
