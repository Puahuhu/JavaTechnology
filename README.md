# SpringCommerce

SpringCommerce là một ứng dụng web đơn giản dùng để bán những sản phẩm Game bản quyền của Puahuhu Store. Vì nhu cầu về việc mua game bản quyền đang ngày càng gia tăng, Puahuhu Store đã xây dựng SpringCommerce để đáp ứng với các chức năng còn hạn chế.

## Mô hình phát triển phần mềm

Dự án SpringCommerce của Puahuhu Store được áp dụng mô hình phát triển phần mềm Waterfall, phù hợp cho một hệ thống đơn giản và yêu cầu rõ ràng. Mô hình Waterfall có các giai đoạn chính bao gồm:

1. **Phân tích yêu cầu**: Thu thập và xác định các yêu cầu từ đề bài. Dự án sẽ hỗ trợ hiển thị sản phẩm, lọc theo tiêu chí và đặt hàng qua giỏ hàng.
   
2. **Thiết kế hệ thống**: Thiết kế cấu trúc cơ bản của ứng dụng web, bao gồm cơ sở dữ liệu và giao diện người dùng.

3. **Lập trình**: Triển khai các tính năng cốt lõi như hiển thị sản phẩm, lọc, và quản lý giỏ hàng.

4. **Kiểm thử**: Kiểm thử toàn bộ ứng dụng để đảm bảo tính năng hoạt động đúng theo yêu cầu.

5. **Triển khai**: Sau khi kiểm thử thành công, sản phẩm sẽ được triển khai ra thị trường.

## Chức năng chính

1. **Hiển thị sản phẩm**: Giao diện đơn giản hiển thị toàn bộ Game bản quyền mà Puahuhu Store cung cấp. Người dùng có thể lọc và tìm kiếm sản phẩm theo các tiêu chí như danh mục, tên, giá, thể loại và studio làm ra nó.

2. **Giỏ hàng và đặt hàng**: Người dùng có thể thêm sản phẩm vào giỏ hàng và tiến hành đặt hàng.

3. **Thanh toán khi nhận hàng**: Hiện tại ứng dụng chưa hỗ trợ thanh toán trực tuyến, người dùng sẽ thanh toán bằng tiền mặt khi sản phẩm được giao đến.

## Nguyên tắc và thực hành

- **Nguyên tắc đơn giản hóa**: Chỉ tập trung vào các tính năng thiết yếu để đáp ứng yêu cầu cơ bản của một MVP.
- **Tính mở rộng**: Thiết kế hệ thống đơn giản nhưng dễ dàng mở rộng thêm các tính năng khác trong tương lai.
- **Tách biệt logic và giao diện**: Các lớp chức năng và giao diện được thiết kế riêng biệt để dễ dàng bảo trì và nâng cấp.

## Cấu trúc mã nguồn

Dự án SpringCommerce sử dụng cấu trúc chuẩn của Maven với các thư mục và gói như sau:

- **com.example.SpringCommerce**: Đây là gói chính của dự án, chứa các thành phần chính theo mô hình MVC (Model-View-Controller) để tổ chức mã nguồn một cách khoa học và dễ bảo trì.

  - **controller**: Thư mục này chứa các lớp điều khiển, chịu trách nhiệm xử lý các yêu cầu từ phía người dùng, gọi các dịch vụ cần thiết và trả kết quả về cho giao diện người dùng.

  - **model**: Chứa các lớp mô hình đại diện cho các đối tượng dữ liệu trong ứng dụng. Các lớp này ánh xạ với các bảng trong cơ sở dữ liệu và được sử dụng để lưu trữ và truy xuất dữ liệu.

  - **repository**: Thư mục này chứa các lớp thao tác với cơ sở dữ liệu, sử dụng Spring Data JPA để quản lý dữ liệu một cách hiệu quả và tách biệt các thao tác cơ sở dữ liệu khỏi logic của ứng dụng.

  - **service**: Thư mục này chứa các lớp dịch vụ xử lý logic nghiệp vụ của ứng dụng, đóng vai trò trung gian giữa controller và repository, thực hiện các xử lý cần thiết trước khi gửi dữ liệu tới các thành phần khác.

  - **SpringCommerceApplication.java**: Đây là điểm khởi động của ứng dụng Spring Boot, chứa các phương thức để thêm dữ liệu vào cơ sở dữ liệu cũng như là phương thức để chạy ứng dụng.

- **resources**: Thư mục này chứa các tài nguyên cần thiết cho ứng dụng.

  - **static**: Thư mục này chứa các tệp tĩnh bao gồm:
    - **css**: Chứa các tệp CSS để định dạng giao diện người dùng.
    - **js**: Chứa các tệp JavaScript để bổ sung chức năng động cho trang web.
    - **images**: Chứa các hình ảnh sử dụng trong giao diện.

  - **templates**: Thư mục này chứa các tệp HTML sử dụng để hiển thị giao diện người dùng. Spring Boot sẽ sử dụng các tệp này để tạo trang web động, cho phép khách hàng xem sản phẩm, lọc sản phẩm, thêm vào giỏ hàng và thực hiện các thao tác đặt hàng.
 
  - **application.properties**: Đây là nơi sẽ cấu hình cơ sở dữ liệu cho ứng dụng.

Cấu trúc này giúp ứng dụng dễ dàng bảo trì, mở rộng và đảm bảo tính nhất quán trong quy trình phát triển phần mềm theo mô hình Waterfall.

## Các bước để chạy ứng dụng

- Đầu tiên ta sẽ nhấn vào phần Download ZIP để tải dự án về máy
  
     ![image](https://github.com/user-attachments/assets/da6e284a-6bd1-45d9-8382-e6fa2a4bf4f8)

- Sau đó hãy giải nén và mở dự án bằng IntelliJ

     ![image](https://github.com/user-attachments/assets/33f618c4-b291-4c8f-8139-934953b82edb)

- Tiếp đến ta mở Xampp để kết nối vào cơ sở dữ liệu
  
     ![Screenshot 2024-11-14 145315](https://github.com/user-attachments/assets/14e453bf-6268-4d7d-be6f-97fe03e9c21b)

- Kế đến ta sẽ nhấn vào nút chạy như ảnh dưới

     ![image](https://github.com/user-attachments/assets/ec6ca7a3-8005-4cf5-82b8-c6c37c11ba65)

- Cuối cùng ta vào Google Chrome hoặc các trình duyệt khác và chỉa tới đường dẫn localhost:8080, nếu như thành công ta sẽ được dẫn đến trang đăng nhập

     ![image](https://github.com/user-attachments/assets/8d443c05-51ae-4b87-8eb4-f1f3d21f8499)

## Kiểm thử API

- Ta khởi động trang web và Postman để kiểm tra phương thức GET của trang http://localhost:8080/products

  - Sau khi truyền phương thức GET vào Postman ta thu được kết quả là tất cả các sản phẩm có trong cơ sở dữ liệu

     ![image](https://github.com/user-attachments/assets/e1f1bf4a-5397-421d-bfe5-297ce00e143a)

  - Tiếp tục kiểm thử với id product là 1 ta sẽ chỉ có 1 giá trị sản phẩm được trả về
 
    ![image](https://github.com/user-attachments/assets/5b0f1747-9643-41e1-8831-febf73da7498)

  - Tương tự với id product là 2

    ![image](https://github.com/user-attachments/assets/7d2002fc-eb58-4b79-aa9f-2d5edf2dadf8)

  - Kiểm thử với id không tồn tại ta sẽ có kết quả sau
 
    ![image](https://github.com/user-attachments/assets/8519a944-0cbc-4814-a694-8fc47ea6d133)






