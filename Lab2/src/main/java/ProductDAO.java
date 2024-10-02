import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements Repository<Product, Integer> {
    private Connection conn;

    public ProductDAO(Connection conn) {
        this.conn = conn;
    }

    // Thiết lập cơ sở dữ liệu (xóa và tạo lại bảng Product)
    public void setupDatabase() throws SQLException {
        Statement stmt = conn.createStatement();

        // Kiểm tra xem cơ sở dữ liệu ProductManagement có tồn tại không, nếu không thì tạo mới
        stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS ProductManagement");

        // Sử dụng cơ sở dữ liệu ProductManagement
        stmt.executeUpdate("USE ProductManagement");

        // Xóa bảng Product nếu tồn tại và tạo lại
        stmt.executeUpdate("DROP TABLE IF EXISTS Product");
        stmt.executeUpdate("CREATE TABLE Product (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), price DOUBLE)");

        stmt.close();
    }

    @Override
    public Integer add(Product item) {
        String sql = "INSERT INTO Product (name, price) VALUES (?, ?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, item.getName());
            pstmt.setDouble(2, item.getPrice());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);  // Trả về ID tự động
            }
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> readAll() {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM Product";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                productList.add(new Product(id, name, price));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public Product read(Integer id) {
        String sql = "SELECT * FROM Product WHERE id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                return new Product(id, name, price);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Product item) {
        String sql = "UPDATE Product SET name = ?, price = ? WHERE id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, item.getName());
            pstmt.setDouble(2, item.getPrice());
            pstmt.setInt(3, item.getId());
            int rows = pstmt.executeUpdate();
            pstmt.close();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM Product WHERE id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            pstmt.close();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
