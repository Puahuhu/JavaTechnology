package com.example.SpringCommerce.controller;

import com.example.SpringCommerce.model.*;
import com.example.SpringCommerce.repository.*;
import com.example.SpringCommerce.service.CartService;
import com.example.SpringCommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private StudioRepository studioRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @GetMapping("/products")
    public String showProducts(
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "priceMin", required = false) Double priceMin,
            @RequestParam(value = "priceMax", required = false) Double priceMax,
            @RequestParam(value = "brand", required = false) String brand,
            @RequestParam(value = "color", required = false) String color,
            Model model) {

        List<Category> categories = categoryRepository.findAll();
        List<Studio> brands = studioRepository.findAll();
        List<Genre> colors = genreRepository.findAll();

        model.addAttribute("categories", categories);
        model.addAttribute("brands", brands);
        model.addAttribute("colors", colors);

        List<Product> products = productService.filterProducts(category, priceMin, priceMax, brand, color);
        model.addAttribute("products", products);

        model.addAttribute("cartItems", cartService.getCartItems());
        model.addAttribute("totalQuantity", cartService.getTotalQuantity());
        model.addAttribute("totalPrice", cartService.getTotalPrice());

        return "product-list";
    }

    @GetMapping("/product/{id}")
    public String showProductDetails(@PathVariable("id") Integer id, Model model) {
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("product", product);
        return "product-details";
    }

    @GetMapping("/add-to-cart/{id}")
    public String addToCart(@PathVariable("id") Integer id) {
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        cartService.addProductToCart(product);
        return "redirect:/products";
    }

    @GetMapping("/clear-cart")
    public String clearCart() {
        cartService.clearCart();
        return "redirect:/products";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        double totalPrice = cartService.getTotalPrice();
        Order order = new Order(totalPrice);
        orderRepository.save(order);

        List<OrderDetail> orderDetails = cartService.getCartItems().entrySet().stream()
                .map(entry -> new OrderDetail(order, entry.getKey(), entry.getValue(), entry.getKey().getPrice() * entry.getValue()))
                .collect(Collectors.toList());

        orderDetailRepository.saveAll(orderDetails);
        order.setOrderDetails(orderDetails);

        model.addAttribute("order", order);
        model.addAttribute("orderDetails", orderDetails);
        model.addAttribute("totalPrice", totalPrice);

        cartService.clearCart();

        return "order-confirmation";
    }
}
