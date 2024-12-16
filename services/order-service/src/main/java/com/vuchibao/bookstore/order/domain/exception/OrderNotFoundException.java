package com.vuchibao.bookstore.order.domain.exception;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.text.StringSubstitutor;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(message);
    }

    public static OrderNotFoundException forOrderNumber(String orderNumber) {
        Map<String, String> values = new HashMap<>();
        values.put("orderNumber", orderNumber);

        String template = "Order with number ${orderNumber} not found";
        String message = new StringSubstitutor(values).replace(template);

        return new OrderNotFoundException(message);
    }
}
