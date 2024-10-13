package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private HashMap<String, String> accounts;

    public void init() throws ServletException {
        accounts = new HashMap<>();
        accounts.put("admin", "12345");
        accounts.put("user1", "12345");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (accounts.containsKey(username) && accounts.get(username).equals(password)) {
            out.println("<html><body>");
            out.println("<h3>Name/Password match</h3>");
            out.println("</body></html>");
        } else {
            out.println("<html><body>");
            out.println("<h3>Name/Password does not match</h3>");
            out.println("</body></html>");
        }
    }
}