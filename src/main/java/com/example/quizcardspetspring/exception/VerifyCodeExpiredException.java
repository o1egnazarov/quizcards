package com.example.quizcardspetspring.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class VerifyCodeExpiredException extends ResponseStatusException {
    public VerifyCodeExpiredException(HttpStatusCode status) {
        super(status);
    }

    public VerifyCodeExpiredException(HttpStatusCode status, String reason) {
        super(status, reason);
    }
}
