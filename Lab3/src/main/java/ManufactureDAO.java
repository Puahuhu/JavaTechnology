import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class ManufactureDAO {

    public boolean add(Manufacture manufacture) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(manufacture);
            transaction.commit();  // Commit thành công
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();  // Nếu có lỗi thì rollback
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Manufacture manufacture) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(manufacture);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return false;
    }

    public boolean remove(Manufacture manufacture) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(manufacture);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return false;
    }

    public boolean remove(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Manufacture manufacture = session.get(Manufacture.class, id);
            if (manufacture != null) {
                session.delete(manufacture);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return false;
    }

    public List<Manufacture> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Manufacture", Manufacture.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Manufacture get(String id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Manufacture.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean hasManufacturersWithMoreThan100Employees() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Manufacture WHERE employee > 100", Manufacture.class)
                    .setMaxResults(1) // Lấy một kết quả đầu tiên nếu có
                    .uniqueResult() != null;
        }
    }

    public int getTotalEmployees() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT SUM(employee) FROM Manufacture", Long.class)
                    .uniqueResult().intValue();
        }
    }

    public Manufacture getLastManufactureMeetingCriteria() throws InvalidOperationException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Manufacture> manufactures = session.createQuery("FROM Manufacture WHERE employee > 100 ORDER BY id DESC", Manufacture.class)
                    .list();
            if (!manufactures.isEmpty()) {
                return manufactures.get(manufactures.size() - 1);
            } else {
                throw new InvalidOperationException("No manufacturer meets the criteria");
            }
        }
    }
}
