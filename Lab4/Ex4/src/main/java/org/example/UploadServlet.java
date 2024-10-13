package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/upload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
public class UploadServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "uploads";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String applicationPath = request.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;

        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }

        String fileNameParam = request.getParameter("name");
        boolean override = request.getParameter("override") != null;

        String[] allowedExtensions = { "txt", "doc", "docx", "img", "pdf", "rar", "zip" };
        Part filePart = request.getPart("document");
        String fileName = filePart.getSubmittedFileName();
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);

        boolean isValidExtension = false;
        for (String ext : allowedExtensions) {
            if (fileExtension.equalsIgnoreCase(ext)) {
                isValidExtension = true;
                break;
            }
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (!isValidExtension) {
            out.println("<p>Unsupported file extension</p>");
            return;
        }

        String saveFileName = fileNameParam.isEmpty() ? fileName : fileNameParam + "." + fileExtension;
        File file = new File(uploadFilePath + File.separator + saveFileName);

        if (file.exists() && !override) {
            out.println("<p>File already exists</p>");
            return;
        }

        filePart.write(file.getAbsolutePath());

        out.println("<p>File has been uploaded</p>");
        out.println("<p><a href='" + request.getContextPath() + "/uploads/" + saveFileName + "'>Click here to visit the file</a></p>");
    }
}