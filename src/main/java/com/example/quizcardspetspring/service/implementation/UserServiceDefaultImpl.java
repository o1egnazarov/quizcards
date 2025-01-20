package com.example.quizcardspetspring.service.implementation;

import com.example.quizcardspetspring.entity.User;
import com.example.quizcardspetspring.exception.UserAlreadyExistException;
import com.example.quizcardspetspring.exception.UserDoesNotExistException;
import com.example.quizcardspetspring.repository.UserRepository;
import com.example.quizcardspetspring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceDefaultImpl implements UserService {

    private final UserRepository userRepository;

    public User saveUser(User user) {
        if (this.userRepository.existsById(user.getId())) {
            throw new UserAlreadyExistException(
                    HttpStatus.CONFLICT, String.format("User with id %s already exists", user.getId())
            );
        }
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
    public Iterable<User> getUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return this.userRepository.findById(id).orElseThrow(
                () -> new UserDoesNotExistException(
                        HttpStatus.NOT_FOUND,
                        "User does not exist with id = %d.".formatted(id))
        );
    }

    @Override
    public User deleteUserById(Long id) {
        final User user = this.userRepository.findById(id).orElseThrow(
                () -> new UserDoesNotExistException(
                        HttpStatus.NOT_FOUND,
                        "User does not exist with id = %d.".formatted(id))
        );

        this.userRepository.deleteById(id);

        return user;
    }
}
