package com.example.quizcardspetspring.controller;

import com.example.quizcardspetspring.entity.User;
import com.example.quizcardspetspring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("/new")
    public ResponseEntity<Long> registrationUser(@RequestBody User user) {
        return this.userService.createUser(user);
    }

    @GetMapping
    public ResponseEntity<Iterable<User>> getUsers() {
        return this.userService.getUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return this.userService.getUserById(id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable Long id) {
        return this.userService.deleteUserById(id);
    }
}
