package com.example.quizcardspetspring.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyVerifiedException extends ResponseStatusException {
    public UserAlreadyVerifiedException(HttpStatusCode status) {
        super(status);
    }

    public UserAlreadyVerifiedException(HttpStatusCode status, String reason) {
        super(status, reason);
    }
}
