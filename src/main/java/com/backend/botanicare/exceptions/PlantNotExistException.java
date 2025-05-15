package com.backend.botanicare.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class PlantNotExistException extends RuntimeException {

    public PlantNotExistException(String message) {
        super(message);
    }

    public PlantNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}

