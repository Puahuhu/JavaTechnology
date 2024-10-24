package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ProductConfig {

    @Bean
    @Scope("prototype")
    public Product product1() {
        Product product = new Product();
        product.setId(1);
        product.setName("Laptop");
        product.setPrice(1000);
        product.setDescription("A powerful laptop");
        return product;
    }

    @Bean
    @Scope("prototype")
    public Product product2() {
        return new Product(2, "Smartphone", 500, "A modern smartphone");
    }

    @Bean
    public Product product3() {
        return new Product(3, "Tablet", 300, "A portable tablet");
    }
}