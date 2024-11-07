package com.example.Ex1;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String errorMessage;

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            switch (statusCode) {
                case 404:
                    errorMessage = "Page not found.";
                    break;
                case 403:
                    errorMessage = "You don't have permission to access this page.";
                    break;
                case 500:
                    errorMessage = "Internal server error.";
                    break;
                default:
                    errorMessage = "An unexpected error occurred.";
                    break;
            }
        } else {
            errorMessage = "An unexpected error occurred.";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }

    public String getErrorPath() {
        return "/error";
    }
}
