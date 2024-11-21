package com.example.SpringCommerce.service;

import com.example.SpringCommerce.model.Product;
import com.example.SpringCommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> filterAndSortProducts(String category, Double priceMin, Double priceMax, String studio, String genre, String sort) {
        List<Product> products = productRepository.findProductsByFilters(
                category != null && !category.isEmpty() ? category : null,
                priceMin != null && priceMin > 0 ? priceMin : null,
                priceMax != null && priceMax > 0 ? priceMax : null,
                studio != null && !studio.isEmpty() ? studio : null,
                genre != null && !genre.isEmpty() ? genre : null
        );

        switch (sort) {
            case "nameAsc":
                products.sort(Comparator.comparing(Product::getName));
                break;
            case "nameDesc":
                products.sort(Comparator.comparing(Product::getName).reversed());
                break;
            case "priceAsc":
                products.sort(Comparator.comparing(Product::getPrice));
                break;
            case "priceDesc":
                products.sort(Comparator.comparing(Product::getPrice).reversed());
                break;
            default:
                break;
        }

        return products;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }
}
