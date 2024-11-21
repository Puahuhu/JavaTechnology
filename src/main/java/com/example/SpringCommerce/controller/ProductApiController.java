package com.example.SpringCommerce.controller;

import com.example.SpringCommerce.model.Product;
import com.example.SpringCommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductApiController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable("id") Integer id) {
        return productService.getProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id: " + id));
    }

    @PostMapping("/product")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer id, @RequestBody Product updatedProduct) {
        Optional<Product> existingProductOpt = productService.getProductById(id);

        if (existingProductOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Product existingProduct = existingProductOpt.get();
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setImg(updatedProduct.getImg());
        existingProduct.setCategory(updatedProduct.getCategory());
        existingProduct.setStudio(updatedProduct.getStudio());
        existingProduct.setGenre(updatedProduct.getGenre());

        Product savedProduct = productService.saveProduct(existingProduct);
        return ResponseEntity.ok(savedProduct);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Integer id) {
        Optional<Product> existingProductOpt = productService.getProductById(id);

        if (existingProductOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }
}