package com.example.quizcardspetspring.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpRequest {

    @NotBlank
    @Size(min = 4, max = 15)
    private String username;

    @NotEmpty
    private String password;

    @NotBlank
    @Email
    private String email;
}
