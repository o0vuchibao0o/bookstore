package com.vuchibao.bookstore.product.domain.exception;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.text.StringSubstitutor;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }

    public static ProductNotFoundException forCode(String code) {
        Map<String, String> values = new HashMap<>();
        values.put("code", code);

        String template = "Product with code ${code} not found";
        String message = new StringSubstitutor(values).replace(template);

        return new ProductNotFoundException(message);
    }
}
