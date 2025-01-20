package com.example.quizcardspetspring.service;

import com.example.quizcardspetspring.dto.JwtAuthenticationResponse;
import com.example.quizcardspetspring.dto.SignInRequest;
import com.example.quizcardspetspring.dto.SignUpRequest;
import com.example.quizcardspetspring.dto.VerifyRequest;
import com.example.quizcardspetspring.entity.User;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {

    User signIn(SignInRequest request);
    User signUp(SignUpRequest request);
    void verify(VerifyRequest request);
    void resendVerificationCode(String email);

}
