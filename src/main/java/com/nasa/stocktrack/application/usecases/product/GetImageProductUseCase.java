package com.nasa.stocktrack.application.usecases.product;

import com.nasa.stocktrack.application.services.FileStorageService;
import com.nasa.stocktrack.domain.entities.RecoveredFile;

public class GetImageProductUseCase {

    private final FileStorageService fileStorageService;

    public GetImageProductUseCase(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    public RecoveredFile execute(String imageName) {
        return fileStorageService.getFile(imageName);
    }
}
