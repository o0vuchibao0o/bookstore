package com.vuchibao.bookstore.order.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vuchibao.bookstore.order.domain.dto.request.CreateOrderRequest;
import com.vuchibao.bookstore.order.domain.service.OrderService;
import com.vuchibao.bookstore.order.domain.service.SecurityService;
import com.vuchibao.bookstore.order.testdata.TestDataFactory;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(OrderController.class)
class OrderControllerUnitTest {
    @MockBean
    private OrderService orderService;

    @MockBean
    private SecurityService securityService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        BDDMockito.given(securityService.getLoginUsername()).willReturn("user");
    }

    @ParameterizedTest(name = "[{index}]-{0}")
    @MethodSource("createOrderRequestProvider")
    void shouldReturnBadRequestWhenOrderPayloadIsInvalid(CreateOrderRequest request) throws Exception {
        System.out.println("request = " + request);
        BDDMockito.given(orderService.createOrder(
                        ArgumentMatchers.eq("user"), ArgumentMatchers.any(CreateOrderRequest.class)))
                .willReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    static Stream<Arguments> createOrderRequestProvider() {
        return Stream.of(
                Arguments.arguments(Named.named(
                        "Order with Invalid Customer", TestDataFactory.createOrderRequestWithInvalidCustomer())),
                Arguments.arguments(Named.named(
                        "Order with Invalid Delivery Address",
                        TestDataFactory.createOrderRequestWithInvalidDeliveryAddress())),
                Arguments.arguments(
                        Named.named("Order with no items", TestDataFactory.createOrderRequestWithNoItems())));
    }
}
