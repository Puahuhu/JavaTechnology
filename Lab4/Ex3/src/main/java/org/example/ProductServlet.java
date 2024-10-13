package org.example;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/ProductService")
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private List<Product> productList;

    public void init() throws ServletException {
        // Khởi tạo danh sách sản phẩm mẫu
        productList = new ArrayList<>();
        productList.add(new Product(1, "iPhone 11", 549));
        productList.add(new Product(2, "iPhone 13 Pro", 999));
    }

    private void sendAsJson(HttpServletResponse response, Object obj) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private void sendError(HttpServletResponse response, int errorCode, String message) throws IOException {
        Map<String, Object> result = new HashMap<>();
        result.put("code", errorCode);
        result.put("message", message);
        sendAsJson(response, result);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam == null) {
            Map<String, Object> result = new HashMap<>();
            result.put("code", 0);
            result.put("message", "Đọc sản phẩm thành công");
            result.put("data", productList);
            sendAsJson(response, result);
        } else {
            try {
                int id = Integer.parseInt(idParam);
                for (Product product : productList) {
                    if (product.getId() == id) {
                        Map<String, Object> result = new HashMap<>();
                        result.put("code", 0);
                        result.put("message", "Đọc sản phẩm thành công");
                        result.put("data", Arrays.asList(product));
                        sendAsJson(response, result);
                        return;
                    }
                }
                sendError(response, 2, "Không tìm thấy sản phẩm nào với mã số " + id);
            } catch (NumberFormatException e) {
                sendError(response, 1, "ID sản phẩm không hợp lệ");
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));

            // Kiểm tra xem ID đã tồn tại hay chưa
            for (Product product : productList) {
                if (product.getId() == id) {
                    sendError(response, 3, "ID sản phẩm đã tồn tại");
                    return;
                }
            }

            Product newProduct = new Product(id, name, price);
            productList.add(newProduct);
            Map<String, Object> result = new HashMap<>();
            result.put("code", 0);
            result.put("message", "Thêm sản phẩm thành công");
            sendAsJson(response, result);
        } catch (NumberFormatException e) {
            sendError(response, 1, "Dữ liệu sản phẩm không hợp lệ");
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));

            for (Product product : productList) {
                if (product.getId() == id) {
                    product.setName(name);
                    product.setPrice(price);
                    Map<String, Object> result = new HashMap<>();
                    result.put("code", 0);
                    result.put("message", "Cập nhật sản phẩm thành công");
                    sendAsJson(response, result);
                    return;
                }
            }
            sendError(response, 2, "Không tìm thấy sản phẩm nào với mã số " + id);
        } catch (NumberFormatException e) {
            sendError(response, 1, "Dữ liệu sản phẩm không hợp lệ");
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Iterator<Product> iterator = productList.iterator();
            while (iterator.hasNext()) {
                Product product = iterator.next();
                if (product.getId() == id) {
                    iterator.remove();
                    Map<String, Object> result = new HashMap<>();
                    result.put("code", 0);
                    result.put("message", "Xóa sản phẩm thành công");
                    sendAsJson(response, result);
                    return;
                }
            }
            sendError(response, 2, "Không tìm thấy sản phẩm nào với mã số " + id);
        } catch (NumberFormatException e) {
            sendError(response, 1, "ID sản phẩm không hợp lệ");
        }
    }
}