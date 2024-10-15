package org.example.servlet;

import org.example.model.User;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("register.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("passwordConfirm");

        if (password == null || passwordConfirm == null || !password.equals(passwordConfirm)) {
            request.setAttribute("error", "Passwords do not match or are invalid!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        if (password.length() < 6) {
            request.setAttribute("error", "Password must be at least 6 characters!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Lưu người dùng vào cơ sở dữ liệu
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        User user = new User(fullname, email, password);
        session.save(user);

        transaction.commit();
        session.close();

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("user", fullname);

        response.sendRedirect("jsp/index.jsp");
    }
}
