package com.nasa.stocktrack.domain.entities;

import java.io.InputStream;

public class FileData {

    private final String filename;

    private final InputStream content;

    public FileData(String filename, InputStream content) {
        this.filename = filename;
        this.content = content;
    }

    public String getFilename() {
        return filename;
    }

    public InputStream getContent() {
        return content;
    }
}
