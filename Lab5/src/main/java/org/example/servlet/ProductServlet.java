package org.example.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dao.ProductDAO;
import org.example.model.Product;

import java.io.IOException;
import java.util.List;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy danh sách sản phẩm từ cơ sở dữ liệu qua DAO
        List<Product> productList = ProductDAO.getAllProducts();

        // Gửi danh sách sản phẩm tới JSP thông qua request
        request.setAttribute("productList", productList);

        // Forward tới file JSP để hiển thị
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/index.jsp");
        dispatcher.forward(request, response);
    }
}
