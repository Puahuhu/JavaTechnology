package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ProductConfig.class);

        Product product1a = context.getBean("product1", Product.class);
        Product product1b = context.getBean("product1", Product.class);

        Product product2a = context.getBean("product2", Product.class);
        Product product2b = context.getBean("product2", Product.class);

        Product product3a = context.getBean("product3", Product.class);
        Product product3b = context.getBean("product3", Product.class);

        System.out.println("First Product 1 instance: " + product1a);
        System.out.println("Second Product 1 instance: " + product1b);

        System.out.println("First Product 2 instance: " + product2a);
        System.out.println("Second Product 2 instance: " + product2b);

        System.out.println("First Product 3 (singleton) instance: " + product3a);
        System.out.println("Second Product 3 (singleton) instance: " + product3b);

        System.out.println("Product 1 objects are prototype: " + (product1a != product1b));
        System.out.println("Product 2 objects are prototype: " + (product2a != product2b));
        System.out.println("Product 3 objects are singleton: " + (product1a == product1b));
    }
}