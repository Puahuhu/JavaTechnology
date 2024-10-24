package org.example;

import org.springframework.stereotype.Component;

@Component("plainTextWriter")
public class PlainTextWriter implements TextWriter {

    @Override
    public void write(String fileName, String text) {
        System.out.println("Writing plain text to file: " + fileName);
        System.out.println("Text Content: " + text);
    }
}
