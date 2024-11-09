package com.example.SpringCommerce.controller;

import com.example.SpringCommerce.model.Account;
import com.example.SpringCommerce.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(String username, String password, Model model) {
        Account account = accountRepository.findByUsernameAndPassword(username, password);

        if (account != null) {
            return "redirect:/products";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
}