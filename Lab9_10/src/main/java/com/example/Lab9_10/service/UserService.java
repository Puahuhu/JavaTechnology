package com.example.Lab9_10.service;

import com.example.Lab9_10.dto.LoginRequest;
import com.example.Lab9_10.model.User;
import com.example.Lab9_10.repository.UserRepository;
import com.example.Lab9_10.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public String login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return JwtUtil.generateToken(user.getEmail());  // Pass email (String) instead of User object
        }
        throw new RuntimeException("Invalid credentials");
    }
}
