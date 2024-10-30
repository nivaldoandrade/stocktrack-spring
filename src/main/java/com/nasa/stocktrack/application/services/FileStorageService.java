package com.nasa.stocktrack.application.services;

import com.nasa.stocktrack.application.gateways.FileStorageGateway;

import java.io.InputStream;

import static com.nasa.stocktrack.application.gateways.FileStorageGateway.generateFileName;

public class FileStorageService {

    private final FileStorageGateway fileStorageGateway;

    public FileStorageService(FileStorageGateway fileStorageGateway) {
        this.fileStorageGateway = fileStorageGateway;
    }

    public String generateFilename(String originalFilename) {
        return generateFileName(originalFilename);
    }

    public void saveFile(InputStream content, String filename) {
        fileStorageGateway.saveFile(content, filename);
    }

    public void deleteFile(String filename) {
        fileStorageGateway.deleteFile(filename);
    }

}
