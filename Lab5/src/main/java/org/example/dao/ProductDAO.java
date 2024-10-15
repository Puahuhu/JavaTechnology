package org.example.dao;

import org.example.model.Product;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProductDAO {
    public static List<Product> getAllProducts() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<Product> productList = null;

        try {
            tx = session.beginTransaction();
            productList = session.createQuery("from Product", Product.class).list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return productList;
    }
}
