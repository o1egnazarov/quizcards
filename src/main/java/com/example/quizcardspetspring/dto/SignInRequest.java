package com.example.quizcardspetspring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignInRequest {

    @NotBlank
    @Size(min = 4)
    private String username;

    @NotEmpty
    private String password;
}
