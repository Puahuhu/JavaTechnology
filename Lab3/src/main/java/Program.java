import java.util.List;

public class Program {
    public static void main(String[] args) {
        // Tạo đối tượng DAO để thực hiện các thao tác CRUD
        PhoneDAO phoneDAO = new PhoneDAO();
        ManufactureDAO manufactureDAO = new ManufactureDAO();

        try {
            // Ví dụ thêm Manufacture
            Manufacture manufacture1 = new Manufacture();
            manufacture1.setId("M001");
            manufacture1.setName("Tech Manufacturer");
            manufacture1.setLocation("Hanoi");
            manufacture1.setEmployee(200);

            Manufacture manufacture2 = new Manufacture();
            manufacture2.setId("M002");
            manufacture2.setName("Mobile Manufacturer");
            manufacture2.setLocation("HCMC");
            manufacture2.setEmployee(150);

            manufactureDAO.addManufacture(manufacture1);
            manufactureDAO.addManufacture(manufacture2);

            // Ví dụ thêm Phone
            Phone phone1 = new Phone();
            phone1.setId("P001");
            phone1.setName("Phone X");
            phone1.setPrice(15000000);
            phone1.setColor("Black");
            phone1.setCountry("Vietnam");
            phone1.setQuantity(10);
            phone1.setManufacture(manufacture1);
            phoneDAO.addPhone(phone1);

            Phone phone2 = new Phone();
            phone2.setId("P002");
            phone2.setName("Phone Y");
            phone2.setPrice(12000000);
            phone2.setColor("Pink");
            phone2.setCountry("Vietnam");
            phone2.setQuantity(5);
            phone2.setManufacture(manufacture2);
            phoneDAO.addPhone(phone2);

            // Đọc dữ liệu từ bảng Phone
            System.out.println("Phone X details: ");
            Phone phone = phoneDAO.getPhoneById("P001");
            if (phone != null) {
                System.out.println("ID: " + phone.getId());
                System.out.println("Name: " + phone.getName());
                System.out.println("Price: " + phone.getPrice());
                System.out.println("Color: " + phone.getColor());
                System.out.println("Country: " + phone.getCountry());
                System.out.println("Quantity: " + phone.getQuantity());
                System.out.println("Manufacture: " + phone.getManufacture().getName());
            }

            // Cập nhật thông tin Phone
            phone.setPrice(14000000);  // Thay đổi giá của Phone X
            phoneDAO.updatePhone(phone);

            // Kiểm tra phương thức tìm điện thoại có giá trên 50 triệu
            boolean hasExpensivePhone = phoneDAO.hasPhoneAbovePrice(50000000);
            System.out.println("Có điện thoại nào giá trên 50 triệu? " + (hasExpensivePhone ? "Yes" : "No"));

            // Xóa Phone
            phoneDAO.deletePhone(phone2);  // Xóa Phone Y

            // Kiểm tra danh sách các điện thoại theo quốc gia
            List<Phone> phonesInVietnam = phoneDAO.getPhonesByCountry("Vietnam");
            System.out.println("Danh sách điện thoại tại Việt Nam: ");
            for (Phone p : phonesInVietnam) {
                System.out.println("Phone Name: " + p.getName() + ", Price: " + p.getPrice());
            }

            // Phương thức kiểm tra nhà sản xuất có trên 100 nhân viên
            boolean hasBigManufacture = manufactureDAO.hasManufacturersWithMoreThan100Employees();
            System.out.println("Có nhà sản xuất nào có hơn 100 nhân viên không? " + (hasBigManufacture ? "Yes" : "No"));

            // Tổng số nhân viên của tất cả các nhà sản xuất
            int totalEmployees = manufactureDAO.getTotalEmployees();
            System.out.println("Tổng số nhân viên của tất cả nhà sản xuất: " + totalEmployees);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
