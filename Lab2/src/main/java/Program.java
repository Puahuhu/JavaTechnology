import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Vui lòng cung cấp URL kết nối qua dòng lệnh.");
            return;
        }

        String url = args[0];
        String user = "root";  // Tùy theo cấu hình MySQL
        String password = "";  // Tùy theo cấu hình MySQL

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            ProductDAO productDAO = new ProductDAO(conn);
            productDAO.setupDatabase(); // Thiết lập cơ sở dữ liệu

            Scanner scanner = new Scanner(System.in);
            int choice;

            do {
                // Hiển thị menu
                System.out.println("1. Read all products");
                System.out.println("2. Read product by id");
                System.out.println("3. Add a new product");
                System.out.println("4. Update a product");
                System.out.println("5. Delete a product");
                System.out.println("6. Exit");
                System.out.print("Your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Xử lý dòng trống

                switch (choice) {
                    case 1:
                        List<Product> productList = productDAO.readAll();
                        for (Product product : productList) {
                            System.out.println(product);
                        }
                        break;
                    case 2:
                        System.out.print("Nhập id sản phẩm: ");
                        int id = scanner.nextInt();
                        Product product = productDAO.read(id);
                        if (product != null) {
                            System.out.println(product);
                        } else {
                            System.out.println("Sản phẩm không tồn tại.");
                        }
                        break;
                    case 3:
                        System.out.print("Nhập tên sản phẩm: ");
                        String name = scanner.nextLine();
                        System.out.print("Nhập giá sản phẩm: ");
                        double price = scanner.nextDouble();
                        int newId = productDAO.add(new Product(0, name, price));
                        System.out.println("Sản phẩm đã được thêm với id: " + newId);
                        break;
                    case 4:
                        System.out.print("Nhập id sản phẩm cần cập nhật: ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine();  // Xử lý dòng trống
                        System.out.print("Nhập tên mới: ");
                        String newName = scanner.nextLine();
                        System.out.print("Nhập giá mới: ");
                        double newPrice = scanner.nextDouble();
                        boolean updated = productDAO.update(new Product(updateId, newName, newPrice));
                        if (updated) {
                            System.out.println("Sản phẩm đã được cập nhật.");
                        } else {
                            System.out.println("Cập nhật thất bại.");
                        }
                        break;
                    case 5:
                        System.out.print("Nhập id sản phẩm cần xóa: ");
                        int deleteId = scanner.nextInt();
                        boolean deleted = productDAO.delete(deleteId);
                        if (deleted) {
                            System.out.println("Sản phẩm đã được xóa.");
                        } else {
                            System.out.println("Xóa thất bại.");
                        }
                        break;
                    case 6:
                        System.out.println("Thoát chương trình.");
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ.");
                }
            } while (choice != 6);

            scanner.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
