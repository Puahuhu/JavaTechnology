package com.example.SpringCommerce.controller;

import jakarta.servlet.http.HttpSession;

import com.example.SpringCommerce.model.Account;
import com.example.SpringCommerce.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

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
    public String login(String username, String password, Model model, HttpSession session) {
        Account account = accountRepository.findByUsernameAndPassword(username, password);

        if (account != null) {
            session.setAttribute("currentUser", account);
            return "redirect:/products";
        } else {
            model.addAttribute("error", "Sai tài khoản hoặc mật khẩu");
            return "login";
        }
    }

    @PostMapping("/logout")
    public String handleLogout() {
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String handleRegister(@ModelAttribute Account account,
                                 @RequestParam("password-repeat") String passwordRepeat,
                                 Model model) {
        if (!account.getPassword().equals(passwordRepeat)) {
            model.addAttribute("error", "Mật khẩu không khớp");
            return "register";
        }

        if (accountRepository.existsByUsername(account.getUsername())) {
            model.addAttribute("error", "Tên tài khoản đã tồn tại");
            return "register";
        }

        account.setAvatar("images/avatar.png");
        accountRepository.save(account);

        return "redirect:/login";
    }
}