package com.example.quizcardspetspring.controller;

import com.example.quizcardspetspring.dto.JwtAuthenticationResponse;
import com.example.quizcardspetspring.dto.SignUpRequest;
import com.example.quizcardspetspring.dto.VerifyRequest;
import com.example.quizcardspetspring.entity.User;
import com.example.quizcardspetspring.service.AuthenticationService;
import com.example.quizcardspetspring.service.implementation.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationServiceJwtImpl;
    private final JwtService jwtService;

    @PostMapping("/signUp")
    public ResponseEntity<User> signUp(@RequestBody SignUpRequest request) {
        final User user = this.authenticationServiceJwtImpl.signUp(request);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PostMapping("/signIn")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody SignUpRequest request) {
        final User user = this.authenticationServiceJwtImpl.signUp(request);
        String jwtToken = this.jwtService.generateToken(user);
        return ResponseEntity.ok().body(new JwtAuthenticationResponse(jwtToken));
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestBody VerifyRequest request) {
        try {
            this.authenticationServiceJwtImpl.verify(request);
            return ResponseEntity.ok("Account verified successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/resend")
    public ResponseEntity<?> resendVerificationCode(@RequestParam String email) {
        try {
            this.authenticationServiceJwtImpl.resendVerificationCode(email);
            return ResponseEntity.ok("Verification code sent.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
