<%@ page import="java.util.List" %>
<%@ page import="org.example.model.Product" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Danh sách sản phẩm</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body style="background-color: #f8f8f8">

<div class="container-fluid p-5">
    <div class="row mb-5">
        <div class="col-md-6">
            <h3>Product Management</h3>
        </div>
        <div class="col-md-6 text-right">
            Xin chào <span class="text-danger">
                <%
                    String fullname = (String) session.getAttribute("user");
                    if (fullname != null) {
                        out.write(fullname);
                    } else {
                        out.write("Username");
                    }
                %>
            </span> | <a href="../LogoutServlet">Logout</a>
        </div>
    </div>
    <div class="row rounded border p-3">
        <div class="col-md-4">
            <h4 class="text-success">Thêm sản phẩm mới</h4>
            <form class="mt-3" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="product-name">Tên sản phẩm</label>
                    <input class="form-control" type="text" placeholder="Nhập tên sản phẩm" id="product-name" name="name">
                </div>
                <div class="form-group">
                    <label for="price">Giá sản phẩm</label>
                    <input class="form-control" type="number" placeholder="Nhập giá bán" id="price" name="price">
                </div>
                <div class="form-group">
                    <button class="btn btn-success mr-2">Thêm sản phẩm</button>
                </div>

                <div class="alert alert-danger">
                    Vui lòng nhập tên sản phẩm
                </div>
            </form>
        </div>
        <div class="col-md-8">
            <h4 class="text-success">Danh sách sản phẩm</h4>
            <p>Chọn một sản phẩm cụ thể để xem chi tiết</p>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>STT</th>
                    <th>Tên sản phẩm</th>
                    <th>Giá</th>
                    <th>Thao tác</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<Product> products = (List<Product>) request.getAttribute("productList");
                    int count = 1;
                    if (products != null) {
                        for (Product product : products) {
                            out.println("<tr>");
                            out.println("<td>" + (count++) + "</td>");
                            out.println("<td>" + product.getName() + "</td>");
                            out.println("<td>" + product.getPrice() + "</td>");
                            out.println("<td><a href='ProductServlet?action=delete&id=" + product.getId() + "'>Xóa</a></td>");
                            out.println("</tr>");
                        }
                    } else {
                        out.println("<tr><td colspan='4'>Không có sản phẩm nào</td></tr>");
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>