package com.kodilla.rentalcars.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Extras not found")
public class ExtrasNotFoundException extends Exception {
    public ExtrasNotFoundException(String message) {
        super(message);
    }

    public ExtrasNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
