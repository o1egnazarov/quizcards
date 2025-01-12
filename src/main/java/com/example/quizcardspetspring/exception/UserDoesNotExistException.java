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

    public UserDoesNotExistException(int rawStatusCode, String reason, Throwable cause) {
        super(rawStatusCode, reason, cause);
    }

    public UserDoesNotExistException(HttpStatusCode status, String reason, Throwable cause) {
        super(status, reason, cause);
    }

    protected UserDoesNotExistException(HttpStatusCode status, String reason, Throwable cause, String messageDetailCode, Object[] messageDetailArguments) {
        super(status, reason, cause, messageDetailCode, messageDetailArguments);
    }
}
