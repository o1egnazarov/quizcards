package com.example.quizcardspetspring.util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
@RequiredArgsConstructor
public class ValidationUtils {
    private final Validator validator;


    public <T> void validationRequest(T request) {
        if (request != null) {
            Set<ConstraintViolation<T>> resultValidation = this.validator.validate(request);
            if (!resultValidation.isEmpty()) {
                String result = resultValidation
                        .stream()
                        .map(ConstraintViolation::getMessage)
                        .reduce((s1, s2) -> s1 + "\n " + s2)
                        .orElse("");
                throw new IllegalArgumentException(result);
            }
        }
    }
}