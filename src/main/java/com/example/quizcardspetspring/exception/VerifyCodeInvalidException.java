package com.example.quizcardspetspring.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class VerifyCodeInvalidException extends ResponseStatusException {
    public VerifyCodeInvalidException(HttpStatusCode status) {
        super(status);
    }

    public VerifyCodeInvalidException(HttpStatusCode status, String reason) {
        super(status, reason);
    }
}
