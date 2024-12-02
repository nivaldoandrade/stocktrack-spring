package com.nasa.stocktrack.application.gateways;

import com.nasa.stocktrack.domain.entities.RecoveredFile;

import java.io.InputStream;

public interface FileStorageGateway {

    RecoveredFile getFile(String filename);

    void saveFile(InputStream content, String filename);

    void deleteFile(String filename);

    static String generateFileName(String originalFilename) {
        return System.currentTimeMillis() + "_" + originalFilename;
    }
}
