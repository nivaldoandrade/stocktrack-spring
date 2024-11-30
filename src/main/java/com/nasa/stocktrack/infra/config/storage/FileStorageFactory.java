package com.nasa.stocktrack.infra.config.storage;

import com.nasa.stocktrack.infra.config.storage.FileStorageProperties.StorageType;
import com.nasa.stocktrack.application.gateways.FileStorageGateway;
import com.nasa.stocktrack.infra.gateways.storage.LocalStorageGateway;
import com.nasa.stocktrack.infra.gateways.storage.S3StorageGateway;
import org.springframework.stereotype.Component;

@Component
public class FileStorageFactory {
    private final FileStorageProperties fileStorageProperties;

    public FileStorageFactory(FileStorageProperties fileStorageProperties) {
        this.fileStorageProperties = fileStorageProperties;
    }

    public FileStorageGateway fileStorageGateway() {
        StorageType storageType = fileStorageProperties.getType();

        return switch (storageType) {
            case S3:
                yield new S3StorageGateway(fileStorageProperties);
            case LOCAL:
                yield new LocalStorageGateway(fileStorageProperties);
        };
    }
}
