package com.example.lab9_10.controller;

import com.example.lab9_10.entity.UserAccount;
import com.example.lab9_10.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserAccount user) {
        Optional<UserAccount> authenticatedUser = userService.login(user.getEmail(), user.getPassword());
        if (authenticatedUser.isPresent()) {
            return ResponseEntity.ok("{\"message\":\"Login Successful\"}");
        } else {
            return ResponseEntity.status(401).body("{\"error\":\"Invalid Credentials\"}");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserAccount> register(@RequestBody UserAccount user) {
        return ResponseEntity.ok(userService.register(user));
    }
}
