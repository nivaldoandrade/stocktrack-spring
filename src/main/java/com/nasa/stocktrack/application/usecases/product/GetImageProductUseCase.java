package com.nasa.stocktrack.application.usecases.product;

import com.nasa.stocktrack.application.services.FileStorageService;

import java.io.InputStream;

public class GetImageProductUseCase {

    private final FileStorageService fileStorageService;

    public GetImageProductUseCase(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    public InputStream execute(String imageName) {
        return fileStorageService.getFile(imageName);
    }
}
