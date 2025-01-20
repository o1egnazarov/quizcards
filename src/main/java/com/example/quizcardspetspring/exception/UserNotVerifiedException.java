package com.example.quizcardspetspring.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class UserNotVerifiedException extends ResponseStatusException {
    public UserNotVerifiedException(HttpStatusCode status) {
        super(status);
    }

    public UserNotVerifiedException(HttpStatusCode status, String reason) {
        super(status, reason);
    }
}
