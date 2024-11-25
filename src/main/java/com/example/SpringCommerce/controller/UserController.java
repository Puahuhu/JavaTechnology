package com.example.SpringCommerce.controller;

import jakarta.servlet.http.HttpSession;

import com.example.SpringCommerce.model.Account;
import com.example.SpringCommerce.model.Order;
import com.example.SpringCommerce.model.OrderDetail;
import com.example.SpringCommerce.repository.AccountRepository;
import com.example.SpringCommerce.repository.OrderDetailRepository;
import com.example.SpringCommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/user-information")
    public String showUserProfile(Model model, HttpSession session) {
        Account account = (Account) session.getAttribute("currentUser");

        if (account == null) {
            throw new IllegalArgumentException("Người dùng chưa đăng nhập.");
        }

        model.addAttribute("account", account);
        return "user-information";
    }

    @GetMapping("/order-history")
    public String showOrderHistory(Model model, HttpSession session) {
        Account currentUser = (Account) session.getAttribute("currentUser");

        if (currentUser == null) {
            throw new IllegalArgumentException("Người dùng chưa đăng nhập.");
        }

        List<Order> orders = orderRepository.findByAccount(currentUser);
        model.addAttribute("orders", orders);

        List<OrderDetail> orderDetails = orders.stream()
                .flatMap(order -> order.getOrderDetails().stream())
                .collect(Collectors.toList());

        model.addAttribute("orderDetails", orderDetails);

        return "order-history";
    }

    @GetMapping("/order-confirmation/{id}")
    public String showOrderConfirmation(@PathVariable("id") Long orderId, Model model, HttpSession session) {
        Account currentUser = (Account) session.getAttribute("currentUser");

        if (currentUser == null) {
            throw new IllegalArgumentException("Người dùng chưa đăng nhập.");
        }

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order ID: " + orderId));

        if (!order.getAccount().equals(currentUser)) {
            throw new IllegalArgumentException("Bạn không có quyền truy cập vào đơn hàng này.");
        }

        List<OrderDetail> orderDetails = order.getOrderDetails();
        String formattedTotalPrice = order.getFormattedTotalPrice();

        model.addAttribute("order", order);
        model.addAttribute("orderDetails", orderDetails);
        model.addAttribute("formattedTotalPrice", formattedTotalPrice);

        return "order-confirmation";
    }
}