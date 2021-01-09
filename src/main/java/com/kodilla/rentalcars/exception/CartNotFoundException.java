package com.kodilla.rentalcars.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Cart not found")
public class CartNotFoundException extends Exception {
    public CartNotFoundException(String message) {
        super(message);
    }

    public CartNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
