package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String birthday = request.getParameter("birthday");
        String birthtime = request.getParameter("birthtime");
        String gender = request.getParameter("gender");
        String country = request.getParameter("country");
        String[] favoriteIde = request.getParameterValues("favorite_ide[]");
        String toeicScore = request.getParameter("toeic");
        String message = request.getParameter("message");

        if (name == null || name.isEmpty() || email == null || email.isEmpty() ||
                birthday == null || birthday.isEmpty() || birthtime == null || birthtime.isEmpty() ||
                gender == null || country == null || "Select a country".equals(country) ||
                favoriteIde == null || favoriteIde.length == 0 || toeicScore == null) {
            out.println("<p>Error: Missing required information. Please fill out all the fields.</p>");
            return;
        }

        out.println("<table border='1'>");
        out.println("<tr><th>Họ tên</th><td>" + name + "</td></tr>");
        out.println("<tr><th>Email</th><td>" + email + "</td></tr>");
        out.println("<tr><th>Ngày sinh</th><td>" + birthday + "</td></tr>");
        out.println("<tr><th>Giờ sinh</th><td>" + birthtime + "</td></tr>");
        out.println("<tr><th>Giới tính</th><td>" + gender + "</td></tr>");
        out.println("<tr><th>Quốc gia</th><td>" + country + "</td></tr>");

        out.println("<tr><th>IDE Yêu thích</th><td>");
        Arrays.stream(favoriteIde).forEach(ide -> out.println(ide + "<br>"));
        out.println("</td></tr>");

        out.println("<tr><th>Điểm TOEIC</th><td>" + toeicScore + "</td></tr>");

        out.println("<tr><th>Giới thiệu bản thân</th><td>" + message + "</td></tr>");
        out.println("</table>");
    }
}