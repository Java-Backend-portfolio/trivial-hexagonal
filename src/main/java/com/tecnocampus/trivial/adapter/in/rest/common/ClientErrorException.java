package com.tecnocampus.trivial.adapter.in.rest.common;

import org.springframework.http.ResponseEntity;

public class ClientErrorException extends RuntimeException {

    private final ResponseEntity<ErrorEntity> response;

    public ClientErrorException(ResponseEntity<ErrorEntity> response) {
        super(getMessage(response));
        this.response = response;
    }

    private static String getMessage(ResponseEntity<ErrorEntity> response) {
        ErrorEntity body = response.getBody();
        return body != null ? body.errorMessage() : null;
    }

    public ResponseEntity<ErrorEntity> getResponse() {
        return response;
    }
}