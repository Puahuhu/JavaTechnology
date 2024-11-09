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

    // Xử lý yêu cầu trang mặc định (http://localhost:8080)
    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login"; // Chuyển hướng tới trang login
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Hiển thị trang login
    }

    @PostMapping("/login")
    public String login(String username, String password, Model model) {
        // Kiểm tra tài khoản trong cơ sở dữ liệu
        Account account = accountRepository.findByUsernameAndPassword(username, password);

        if (account != null) {
            // Nếu tài khoản hợp lệ, chuyển hướng đến trang sản phẩm
            return "redirect:/products";
        } else {
            // Nếu tài khoản không hợp lệ, hiển thị thông báo lỗi
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
}