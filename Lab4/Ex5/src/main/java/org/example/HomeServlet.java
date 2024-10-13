package org.example;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/")
public class HomeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");

        String destination;
        if (page == null) {
            response.getWriter().println("Welcome to our website");
            return;
        } else {
            switch (page) {
                case "about":
                    destination = "/about.jsp";
                    break;
                case "contact":
                    destination = "/contact.jsp";
                    break;
                case "help":
                    destination = "/help.jsp";
                    break;
                default:
                    response.getWriter().println("Invalid page requested");
                    return;
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
            dispatcher.forward(request, response);
        }
    }
}