package com.example.quizcardspetspring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignInRequest {

    @NotBlank
    @Size(min = 4)
    private String username;

    @NotEmpty
    private String password;
}
