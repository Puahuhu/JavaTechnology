import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Program {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please enter connection URL");
            return;
        }

        String url = args[0];
        String user = "root";
        String password = "";
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Database connected successfully!");

            ProductDAO productDAO = new ProductDAO(conn);
            productDAO.setupDatabase();
            productDAO.displayMenu();

        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}