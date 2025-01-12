package com.example.quizcardspetspring.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyExistByEmailException extends ResponseStatusException {
    public UserAlreadyExistByEmailException(HttpStatusCode status) {
        super(status);
    }

    public UserAlreadyExistByEmailException(HttpStatusCode status, String reason) {
        super(status, reason);
    }

    public UserAlreadyExistByEmailException(int rawStatusCode, String reason, Throwable cause) {
        super(rawStatusCode, reason, cause);
    }

    public UserAlreadyExistByEmailException(HttpStatusCode status, String reason, Throwable cause) {
        super(status, reason, cause);
    }

    protected UserAlreadyExistByEmailException(HttpStatusCode status, String reason, Throwable cause, String messageDetailCode, Object[] messageDetailArguments) {
        super(status, reason, cause, messageDetailCode, messageDetailArguments);
    }
}
