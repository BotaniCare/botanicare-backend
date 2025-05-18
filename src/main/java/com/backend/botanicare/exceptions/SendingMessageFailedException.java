package com.backend.botanicare.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class SendingMessageFailedException extends RuntimeException {

    public SendingMessageFailedException(String message) {
        super(message);
    }

    public SendingMessageFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
