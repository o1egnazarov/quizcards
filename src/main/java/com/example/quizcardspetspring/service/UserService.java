package com.example.quizcardspetspring.service;

import com.example.quizcardspetspring.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService {
    ResponseEntity<Long> createUser(User user);

    ResponseEntity<Iterable<User>> getUsers();

    ResponseEntity<User> getUserById(Long id);

    ResponseEntity<User> deleteUserById(Long id);

    UserDetailsService userDetailsService();
    User getUserByUsername(String username);
    User getUserByEmail(String email);
}
