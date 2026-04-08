package com.constitution.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.constitution.backend.model.User;
import com.constitution.backend.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserService userService;

    // SIGNUP
    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        return userService.register(user);
    }

    // LOGIN
   @PostMapping("/login")
public ResponseEntity<String> login(@RequestBody User user) {

    try {
        String role = userService.login(user.getEmail(), user.getPassword());
        return ResponseEntity.ok(role);

    } catch (RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
}