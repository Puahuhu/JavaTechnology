# SpringCommerce

SpringCommerce là một ứng dụng mua sắm trực tuyến đơn giản dành cho các sản phẩm của công ty. Ứng dụng này được xây dựng nhằm đáp ứng nhu cầu thị trường nhanh chóng với một phiên bản MVP (Minimum Viable Product) có các chức năng giới hạn.

## Mô hình phát triển phần mềm

Dự án này áp dụng mô hình phát triển phần mềm Waterfall, phù hợp cho một hệ thống đơn giản và yêu cầu rõ ràng như SpringCommerce. Mô hình Waterfall có các giai đoạn chính bao gồm:

1. **Phân tích yêu cầu**: Thu thập và xác định các yêu cầu từ đề bài. Dự án sẽ hỗ trợ hiển thị sản phẩm, tìm kiếm theo tiêu chí và đặt hàng qua giỏ hàng.
   
2. **Thiết kế hệ thống**: Thiết kế cấu trúc cơ bản của ứng dụng web, bao gồm cơ sở dữ liệu và giao diện người dùng.

3. **Lập trình**: Triển khai các tính năng cốt lõi như hiển thị sản phẩm, tìm kiếm, và quản lý giỏ hàng.

4. **Kiểm thử**: Kiểm thử toàn bộ ứng dụng để đảm bảo tính năng hoạt động đúng theo yêu cầu.

5. **Triển khai**: Sau khi kiểm thử thành công, sản phẩm sẽ được triển khai ra thị trường.

## Chức năng chính

1. **Hiển thị sản phẩm**: Giao diện đơn giản hiển thị toàn bộ sản phẩm mà công ty cung cấp. Người dùng có thể lọc và tìm kiếm sản phẩm theo các tiêu chí như danh mục, tên, giá, thương hiệu và màu sắc.

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
