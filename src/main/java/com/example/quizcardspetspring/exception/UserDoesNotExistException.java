package com.example.quizcardspetspring.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class UserDoesNotExistException extends ResponseStatusException {

    public UserDoesNotExistException(HttpStatusCode status) {
        super(status);
    }

    public UserDoesNotExistException(HttpStatusCode status, String reason) {
        super(status, reason);
    }

}
