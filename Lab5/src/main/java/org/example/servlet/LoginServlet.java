package org.example.servlet;

import jakarta.servlet.http.*;
import org.example.model.User;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember"); // Lấy giá trị từ checkbox

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE email = :email AND password = :password", User.class);
            query.setParameter("email", email);
            query.setParameter("password", password);

            User user = query.uniqueResult();

            if (user != null) {
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("user", user.getFullname());

                if ("on".equals(remember)) {
                    Cookie userCookie = new Cookie("username", email);
                    Cookie passCookie = new Cookie("password", password);

                    userCookie.setMaxAge(30 * 24 * 60 * 60);
                    passCookie.setMaxAge(30 * 24 * 60 * 60);

                    response.addCookie(userCookie);
                    response.addCookie(passCookie);
                } else {
                    Cookie userCookie = new Cookie("username", "");
                    Cookie passCookie = new Cookie("password", "");

                    userCookie.setMaxAge(0); // Hết hạn cookie
                    passCookie.setMaxAge(0); // Hết hạn cookie

                    response.addCookie(userCookie);
                    response.addCookie(passCookie);
                }

                response.sendRedirect("jsp/index.jsp");
            } else {
                response.sendRedirect("jsp/register.jsp");
            }
        }
    }
}