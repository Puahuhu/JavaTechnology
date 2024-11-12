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
            @RequestParam(value = "studio", required = false) String studio,
            @RequestParam(value = "genre", required = false) String genre,
            @RequestParam(value = "sort", required = false, defaultValue = "default") String sort,
            Model model) {

        List<Category> categories = categoryRepository.findAll();
        List<Studio> studios = studioRepository.findAll();
        List<Genre> genres = genreRepository.findAll();

        model.addAttribute("categories", categories);
        model.addAttribute("studios", studios);
        model.addAttribute("genres", genres);

        Double minPrice = (priceMin != null && priceMin > 0) ? priceMin : null;
        Double maxPrice = (priceMax != null && priceMax > 0) ? priceMax : null;

        List<Product> products = productService.filterAndSortProducts(category, minPrice, maxPrice, studio, genre, sort);
        model.addAttribute("products", products);

        model.addAttribute("cartItems", cartService.getCartItems());
        model.addAttribute("totalQuantity", cartService.getTotalQuantity());
        model.addAttribute("formattedTotalPrice", cartService.getFormattedTotalPrice());

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
    public String addToCart(@PathVariable("id") Integer id, @RequestParam(value = "quantity", defaultValue = "1") int quantity) {
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        cartService.addProductToCart(product, quantity);
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
