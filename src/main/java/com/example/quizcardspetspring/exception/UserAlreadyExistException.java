package com.example.quizcardspetspring.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyExistException extends ResponseStatusException {
    public UserAlreadyExistException(HttpStatusCode status) {
        super(status);
    }

    public UserAlreadyExistException(HttpStatusCode status, String reason) {
        super(status, reason);
    }

    public UserAlreadyExistException(int rawStatusCode, String reason, Throwable cause) {
        super(rawStatusCode, reason, cause);
    }

    public UserAlreadyExistException(HttpStatusCode status, String reason, Throwable cause) {
        super(status, reason, cause);
    }

    protected UserAlreadyExistException(HttpStatusCode status, String reason, Throwable cause, String messageDetailCode, Object[] messageDetailArguments) {
        super(status, reason, cause, messageDetailCode, messageDetailArguments);
    }
}
