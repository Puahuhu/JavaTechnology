package org.example.program;

import org.example.model.Product;
import org.example.dao.ProductDAO;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Program {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
//            for (int i = 1; i <= 10; i++) {
//                User user = new User("Fullname" + i, "user" + i + "@example.com", "password" + i);
//                session.save(user);
//            }
//
//            for (int i = 1; i <= 10; i++) {
//                Product product = new Product("Product" + i, i * 100.0);
//                session.save(product);
//            }
//
//            transaction.commit();
//            System.out.println("Thêm dữ liệu thành công!");

            List<Product> productList = ProductDAO.getAllProducts();
            System.out.println("Danh sách sản phẩm:");
            for (Product product : productList) {
                System.out.println("ID: " + product.getId() + ", Tên: " + product.getName() + ", Giá: " + product.getPrice());
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        HibernateUtil.close();
    }
}