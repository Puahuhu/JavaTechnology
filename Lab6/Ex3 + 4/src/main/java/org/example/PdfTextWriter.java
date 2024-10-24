package org.example;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;

@Component("pdfTextWriter")
public class PdfTextWriter implements TextWriter {

    @Override
    public void write(String fileName, String text) {
        try {
            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream(fileName));

            document.open();

            document.add(new Paragraph(text));

            document.close();

            System.out.println("PDF file created: " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}