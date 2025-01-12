package com.example.quizcardspetspring.service.implementation;

import com.example.quizcardspetspring.dto.JwtAuthenticationResponse;
import com.example.quizcardspetspring.dto.SignInRequest;
import com.example.quizcardspetspring.dto.SignUpRequest;
import com.example.quizcardspetspring.entity.Role;
import com.example.quizcardspetspring.entity.User;
import com.example.quizcardspetspring.service.AuthenticationService;
import com.example.quizcardspetspring.service.JwtService;
import com.example.quizcardspetspring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceJwtImpl implements AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<JwtAuthenticationResponse> signUp(SignUpRequest request) {

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userService.createUser(user);

        String jwt = jwtService.generateToken(user);
        return new ResponseEntity<>(new JwtAuthenticationResponse(jwt), HttpStatus.CREATED);
    }

    public  ResponseEntity<JwtAuthenticationResponse> signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));
        // FIXME: 07.09.2024 
        UserDetails user = this.userService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        String jwt = jwtService.generateToken(user);
        return new ResponseEntity<>(new JwtAuthenticationResponse(jwt), HttpStatus.ACCEPTED);
    }
}

