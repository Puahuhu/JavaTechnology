import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class PhoneDAO {

    public boolean addPhone(Phone phone) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(phone);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatePhone(Phone phone) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(phone);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return false;
    }

    public boolean deletePhone(Phone phone) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(phone);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return false;
    }

    public Phone getPhoneById(String id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Phone.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Phone> getPhonesByCountry(String country) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Phone WHERE country = :country ORDER BY price DESC", Phone.class)
                    .setParameter("country", country)
                    .list();
        }
    }

    public Phone getPhoneWithHighestPrice() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Phone ORDER BY price DESC", Phone.class)
                    .setMaxResults(1)
                    .uniqueResult();
        }
    }

    public boolean hasPhoneAbovePrice(int price) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Phone WHERE price > :price", Phone.class)
                    .setParameter("price", price)
                    .uniqueResult() != null;
        }
    }

    public Phone getPhoneWithCriteria() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Phone WHERE color = 'Pink' AND price > 15000000", Phone.class)
                    .setMaxResults(1)
                    .uniqueResult();
        }
    }
}
