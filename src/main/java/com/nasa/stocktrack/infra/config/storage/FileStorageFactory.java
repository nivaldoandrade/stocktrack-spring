package com.nasa.stocktrack.infra.config.storage;

import com.nasa.stocktrack.application.gateways.FileStorageGateway;
import com.nasa.stocktrack.infra.gateways.storage.LocalStorageGateway;
import com.nasa.stocktrack.infra.gateways.storage.S3StorageGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileStorageFactory {

    @Value("${storage.type}")
    private String storageType;

    private final LocalStorageGateway localStorageGateway;

    private final S3StorageGateway s3StorageGateway;

    public FileStorageFactory(
            LocalStorageGateway localStorageGateway,
            S3StorageGateway s3StorageGateway
    ) {
        this.localStorageGateway = localStorageGateway;
        this.s3StorageGateway = s3StorageGateway;
    }

    public FileStorageGateway fileStorageGateway() {
        System.out.println(storageType);

        return switch (storageType) {
            case "s3":
                yield s3StorageGateway;
            case "local":
                yield localStorageGateway;
            default:
                throw new IllegalArgumentException("Invalid file storage type: " + storageType);
        };
    }
}
