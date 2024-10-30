package com.nasa.stocktrack.application.gateways;

import java.io.InputStream;

public interface FileStorageGateway {
    void saveFile(InputStream content, String filename);

    void deleteFile(String filename);

    static String generateFileName(String originalFilename) {
        return System.currentTimeMillis() + "_" + originalFilename;
    }
}
