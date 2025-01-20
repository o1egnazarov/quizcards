package com.example.quizcardspetspring.service.implementation;

import com.example.quizcardspetspring.entity.User;
import com.example.quizcardspetspring.exception.UserAlreadyExistException;
import com.example.quizcardspetspring.exception.UserDoesNotExistException;
import com.example.quizcardspetspring.repository.UserRepository;
import com.example.quizcardspetspring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceDefaultImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<Long> createUser(User user) {
        if (this.userRepository.existsByEmail(user.getUsername())) {
            throw new UserAlreadyExistException(
                    HttpStatus.CONFLICT,
                    "User with name: %s already exists".formatted(user.getUsername())
            );
        }

        return new ResponseEntity<>(this.userRepository.save(user).getId(), HttpStatus.CREATED);
    }

    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    public UserDetailsService userDetailsService() {
        return this::getUserByEmail;
    }

    @Override
    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(
                () -> new UserDoesNotExistException(
                        HttpStatus.NOT_FOUND,
                        "User does not exist with email = %s".formatted(email))
        );
    }

    @Override
    public ResponseEntity<Iterable<User>> getUsers() {
        return new ResponseEntity<>(this.userRepository.findAll(), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<User> getUserById(Long id) {
        User user = this.userRepository
                .findById(id)
                .orElseThrow(() -> new UserDoesNotExistException(
                        HttpStatus.NOT_FOUND,
                        "user does not exist with id = %d".formatted(id)));

        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);

    }

    @Override
    public ResponseEntity<User> deleteUserById(Long id) {
        User user = this.userRepository
                .findById(id)
                .orElseThrow(() -> new UserDoesNotExistException(HttpStatus.NOT_FOUND,
                        "user does not exist with id = %d".formatted(id)));

        this.userRepository.deleteById(id);

        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }
}
