package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("file");
        String speedParam = request.getParameter("speed");

        if (fileName == null || fileName.isEmpty()) {
            response.getWriter().write("File not found");
            return;
        }

        InputStream fileStream = getServletContext().getResourceAsStream("/WEB-INF/classes/" + fileName);

        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream");

        int speed = speedParam != null ? Integer.parseInt(speedParam) : Integer.MAX_VALUE;

        try (OutputStream out = response.getOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            long startTime = System.currentTimeMillis();

            while ((bytesRead = fileStream.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);

                long elapsedTime = System.currentTimeMillis() - startTime;
                long expectedTime = (bytesRead * 1000L) / speed;

                if (elapsedTime < expectedTime) {
                    try {
                        Thread.sleep(expectedTime - elapsedTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                startTime = System.currentTimeMillis();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}