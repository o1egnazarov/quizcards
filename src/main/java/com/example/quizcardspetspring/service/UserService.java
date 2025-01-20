package com.example.quizcardspetspring.service;

import com.example.quizcardspetspring.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService {
    User saveUser(User user);

    Iterable<User> getUsers();

    User getUserById(Long id);

    User deleteUserById(Long id);

    UserDetailsService userDetailsService();

    User getUserByEmail(String email);
}
