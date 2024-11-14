package com.example.SpringCommerce.controller;

import com.example.SpringCommerce.model.Account;
import com.example.SpringCommerce.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/user-information")
    public String showUserProfile(Model model) {
        Account account = accountRepository.findById(1).orElseThrow(() -> new IllegalArgumentException("Invalid account ID"));

        model.addAttribute("account", account);
        return "user-information";
    }
}