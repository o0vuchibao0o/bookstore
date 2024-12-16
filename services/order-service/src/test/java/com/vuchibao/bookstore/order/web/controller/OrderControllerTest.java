package com.vuchibao.bookstore.order.web.controller;

import com.vuchibao.bookstore.order.AbstractIT;
import com.vuchibao.bookstore.order.testdata.TestDataFactory;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class OrderControllerTest extends AbstractIT {
    @Nested
    class CreateOrderTest {
        @Test
        void shouldCreateOrderSuccessfully() {
            var payload =
                    """
                {
                    "customer" : {
                        "name": "Siva",
                        "email": "siva@gmail.com",
                        "phone": "999999999"
                    },
                    "deliveryAddress" : {
                        "addressLine1": "HNO 123",
                        "addressLine2": "Kukatpally",
                        "city": "Hyderabad",
                        "state": "Telangana",
                        "zipCode": "500072",
                        "country": "India"
                    },
                    "items": [
                        {
                            "code": "P100",
                            "name": "Product 1",
                            "price": 25.50,
                            "quantity": 1
                        }
                    ]
                }
            """;
            RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(payload)
                    .when()
                    .post("/api.orders")
                    .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .body("orderNumber", Matchers.notNullValue());
        }
    }

    @Test
    void shouldReturnBadRequestWhenMandatoryDataIsMissing() {
        var payload = TestDataFactory.createOrderRequestWithInvalidCustomer();
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/api.orders")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}
