package com.example.quizcardspetspring.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VerifyRequest {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String verifyCode;
}
