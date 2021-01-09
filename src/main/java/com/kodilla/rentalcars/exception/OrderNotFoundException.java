package com.kodilla.rentalcars.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Order not found")
public class OrderNotFoundException extends Exception {
    public OrderNotFoundException(String message) {
        super(message);
    }

    public OrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}