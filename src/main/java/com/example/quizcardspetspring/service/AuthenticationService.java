package com.example.quizcardspetspring.service;

import com.example.quizcardspetspring.dto.JwtAuthenticationResponse;
import com.example.quizcardspetspring.dto.SignInRequest;
import com.example.quizcardspetspring.dto.SignUpRequest;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {

    ResponseEntity<JwtAuthenticationResponse> signIn(SignInRequest request);
    ResponseEntity<JwtAuthenticationResponse> signUp(SignUpRequest request);
}
