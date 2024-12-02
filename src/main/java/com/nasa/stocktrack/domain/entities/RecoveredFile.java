package com.nasa.stocktrack.domain.entities;

import java.io.InputStream;

public class RecoveredFile {
    private String url;

    private InputStream inputStream;

    public RecoveredFile(String url) {
        this.url = url;
    }

    public RecoveredFile(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getUrl() {
        return url;
    }

    public InputStream getInputStream() {
        return inputStream;
    }
}
