package com.example.quizcardspetspring.controller;

import com.example.quizcardspetspring.dto.JwtAuthenticationResponse;
import com.example.quizcardspetspring.dto.SignInRequest;
import com.example.quizcardspetspring.dto.SignUpRequest;
import com.example.quizcardspetspring.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationServiceJwtImpl;

    @PostMapping("/signUp")
    public ResponseEntity<JwtAuthenticationResponse> signUp(@RequestBody SignUpRequest request) {
        return this.authenticationServiceJwtImpl.signUp(request);
    }

    @PostMapping("/signIn")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody SignInRequest request) {
        return this.authenticationServiceJwtImpl.signIn(request);
    }
}
