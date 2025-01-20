package com.example.quizcardspetspring.service.implementation;

import com.example.quizcardspetspring.dto.SignInRequest;
import com.example.quizcardspetspring.dto.SignUpRequest;
import com.example.quizcardspetspring.dto.VerifyRequest;
import com.example.quizcardspetspring.entity.Role;
import com.example.quizcardspetspring.entity.User;
import com.example.quizcardspetspring.exception.UserAlreadyVerifiedException;
import com.example.quizcardspetspring.exception.UserNotVerifiedException;
import com.example.quizcardspetspring.exception.VerifyCodeExpiredException;
import com.example.quizcardspetspring.exception.VerifyCodeInvalidException;
import com.example.quizcardspetspring.service.AuthenticationService;
import com.example.quizcardspetspring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceJwtImpl implements AuthenticationService {
    private static final String VERIFY_MESSAGE_TEMPLATE = """
            <html>
            <body style="font-family: Arial, sans-serif;">
                <div style="background-color: #f5f5f5; padding: 20px;">
                    <h2 style="color: #333;">Welcome to our app!</h2>
                    <p style="font-size: 16px;">Please enter the verification code below to continue:</p>
                    <div style="background-color: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1);">
                        <h3 style="color: #333;">Verification Code:</h3>
                        <p style="font-size: 18px; font-weight: bold; color: #007bff;">%s</p>
                    </div>
                </div>
            </body>
            </html>
            """;

    private final UserService userService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public User signUp(SignUpRequest request) {

        final User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .date(LocalDate.now())
                .role(Role.USER)
                .isEnabled(false)
                .verificationCode(this.generateVerificationCode())
                .verificationCodeExpiresAt(LocalDateTime.now().plusMinutes(15))
                .build();

        this.sendVerificationEmail(user);
        return this.userService.saveUser(user);

    }

    private void sendVerificationEmail(User user) {
        String subject = "Account Verification";
        String verificationCode = "VERIFICATION CODE " + user.getVerificationCode();
        String htmlMessage = String.format(VERIFY_MESSAGE_TEMPLATE, verificationCode);

        try {
            this.emailService.sendVerificationEmail(user.getEmail(), subject, htmlMessage);
        } catch (Exception e) {
            System.out.printf("An error occurred: %s\nFor the reason: %s\n", e.getMessage(), e.getCause());
        }
    }

    public void verify(VerifyRequest request) {
        final User user = this.userService.getUserByEmail(request.getEmail());

        if (user.getVerificationCodeExpiresAt().isBefore(LocalDateTime.now())) {
            throw new VerifyCodeExpiredException(HttpStatus.GONE, "Verification code has expired");
        }

        if (!user.getVerificationCode().equals(request.getVerifyCode())) {
            throw new VerifyCodeInvalidException(HttpStatus.BAD_REQUEST, "Invalid verification code");
        }

        enableUser(user);
    }

    private void enableUser(User user) {
        user.setEnabled(true);
        user.setVerificationCode(null);
        user.setVerificationCodeExpiresAt(null);

        this.userService.saveUser(user);
    }

    public void resendVerificationCode(String email) {
        final User user = this.userService.getUserByEmail(email);

        if (user.isEnabled()) {
            throw new UserAlreadyVerifiedException(
                    HttpStatus.BAD_REQUEST,
                    String.format("User with email: %s, already verified.", email)
            );
        }

        user.setVerificationCode(generateVerificationCode());
        user.setVerificationCodeExpiresAt(LocalDateTime.now().plusMinutes(15));
        this.userService.saveUser(user);

        this.sendVerificationEmail(user);
    }


    public User signIn(SignInRequest request) {
        final User user = this.userService.getUserByEmail(request.getEmail());

        if (!user.isEnabled()) {
            throw new UserNotVerifiedException(
                    HttpStatus.UNAUTHORIZED,
                    String.format("User with email: %s, is not verified.", request.getEmail())
            );
        }

        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));

        return user;
    }

    private String generateVerificationCode() {
        Random random = new Random();
        int code = random.nextInt(900000) + 100000;
        return String.valueOf(code);
    }
}

