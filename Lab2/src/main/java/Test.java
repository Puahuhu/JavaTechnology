import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/Lab02?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Kết nối thành công!");

            String insertSQL = "INSERT INTO student (name, age) VALUES (?, ?)";
            PreparedStatement ptm = conn.prepareStatement(insertSQL);
            ptm.setString(1, "Le Minh");
            ptm.setInt(2, 23);
            int rows = ptm.executeUpdate();

            if (rows == 1) {
                System.out.println("Thêm thành công!");
            }

            String selectSQL = "SELECT * FROM student";
            Statement stmt = conn.createStatement();
            ResultSet data = stmt.executeQuery(selectSQL);

            List<Student> studentList = new ArrayList<>();

            while (data.next()) {
                String name = data.getString("name");
                int age = data.getInt("age");
                Student s = new Student(name, age);
                studentList.add(s);
            }

            for (Student s : studentList) {
                System.out.println(s);
            }

            data.close();
            stmt.close();
            ptm.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
    }
}