package com.backend.botanicare.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PlantNotFoundException extends RuntimeException {

    public PlantNotFoundException(String message) {
        super(message);
    }

    public PlantNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
