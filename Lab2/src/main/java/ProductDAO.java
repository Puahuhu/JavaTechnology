import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductDAO {
    private Connection conn;

    public ProductDAO(Connection conn) {
        this.conn = conn;
    }

    public void setupDatabase() {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS ProductManagement");
            stmt.executeUpdate("USE ProductManagement");

            stmt.executeUpdate("DROP TABLE IF EXISTS Product");
            stmt.executeUpdate(
                    "CREATE TABLE Product (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), price DOUBLE)"
            );

            stmt.executeUpdate("INSERT INTO Product(name, price) VALUES('iPhone 14', 999.99)");
            stmt.executeUpdate("INSERT INTO Product(name, price) VALUES('Samsung Galaxy S21', 799.99)");
            stmt.executeUpdate("INSERT INTO Product(name, price) VALUES('Google Pixel 6', 699.99)");
            stmt.executeUpdate("INSERT INTO Product(name, price) VALUES('Xiaomi Mi 11', 599.99)");
            stmt.executeUpdate("INSERT INTO Product(name, price) VALUES('OnePlus 9 Pro', 969.99)");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addProduct(Product product) {
        String sql = "INSERT INTO Product(name, price) VALUES(?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getPrice());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    product.setId(generatedKeys.getInt(1));
                }
            }
            System.out.println("Product added: " + product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> readAllProducts() {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM Product";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Product product = new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"));
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public Product readProduct(int id) {
        String sql = "SELECT * FROM Product WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateProduct(Product product) {
        String sql = "UPDATE Product SET name = ?, price = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getPrice());
            pstmt.setInt(3, product.getId());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product updated successfully.");
            } else {
                System.out.println("No product found with ID: " + product.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int id) {
        String sql = "DELETE FROM Product WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product deleted successfully.");
            } else {
                System.out.println("No product found with ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 6) {
            System.out.println("1. Read all products");
            System.out.println("2. Read detail of a product by id");
            System.out.println("3. Add a new product");
            System.out.println("4. Update a product");
            System.out.println("5. Delete a product by id");
            System.out.println("6. Exit");
            System.out.print("Your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    List<Product> products = readAllProducts();
                    for (Product p : products) {
                        System.out.println(p);
                    }
                    break;
                case 2:
                    System.out.print("Enter product id: ");
                    int id = scanner.nextInt();
                    Product product = readProduct(id);
                    if (product != null) {
                        System.out.println(product);
                    } else {
                        System.out.println("No product found with ID: " + id);
                    }
                    break;
                case 3:
                    System.out.print("Enter product name: ");
                    String name = scanner.next();
                    System.out.print("Enter product price: ");
                    double price = scanner.nextDouble();
                    addProduct(new Product(name, price));
                    break;
                case 4:
                    System.out.print("Enter product id to update: ");
                    int updateId = scanner.nextInt();
                    System.out.print("Enter new product name: ");
                    String updateName = scanner.next();
                    System.out.print("Enter new product price: ");
                    double updatePrice = scanner.nextDouble();
                    updateProduct(new Product(updateId, updateName, updatePrice));
                    break;
                case 5:
                    System.out.print("Enter product id to delete: ");
                    int deleteId = scanner.nextInt();
                    deleteProduct(deleteId);
                    break;
                case 6:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }

        scanner.close();
    }
}