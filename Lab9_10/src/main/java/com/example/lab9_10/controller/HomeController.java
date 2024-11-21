package com.example.lab9_10.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home"; // File home.html trong thư mục templates
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // File login.html trong thư mục templates
    }
}
