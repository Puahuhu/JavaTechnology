package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("appConfig.xml");

        Product product1a = (Product) context.getBean("product1");
        Product product1b = (Product) context.getBean("product1");

        Product product2a = (Product) context.getBean("product2");
        Product product2b = (Product) context.getBean("product2");

        Product product3a = (Product) context.getBean("product3");
        Product product3b = (Product) context.getBean("product3");

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