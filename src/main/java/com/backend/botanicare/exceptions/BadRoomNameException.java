package com.backend.botanicare.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BadRoomNameException extends RuntimeException {

    public BadRoomNameException(String message) {
        super(message);
    }

    public BadRoomNameException(String message, Throwable cause) {
        super(message, cause);
    }
}
