package com.example.quizcardspetspring.controller;

import com.example.quizcardspetspring.entity.User;
import com.example.quizcardspetspring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/admin")
    public ResponseEntity<Iterable<User>> getUsers() {
        final Iterable<User> users = userService.getUsers();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        final User user = this.userService.getUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable Long id) {
        final User deletedUser = this.userService.deleteUserById(id);
        return ResponseEntity.ok().body(deletedUser);
    }
}
