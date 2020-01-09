package com.fpt.automatedtesting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CustomException extends ResponseStatusException {

    public CustomException(HttpStatus status, String errorMessage) {
        super(status, errorMessage);
    }
}

