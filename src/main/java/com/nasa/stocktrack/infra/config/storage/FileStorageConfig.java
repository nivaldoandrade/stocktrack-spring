package com.nasa.stocktrack.infra.config.storage;

import com.nasa.stocktrack.application.gateways.FileStorageGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileStorageConfig {
    private final FileStorageFactory fileStorageFactory;

    public FileStorageConfig(FileStorageFactory fileStorageFactory) {
        this.fileStorageFactory = fileStorageFactory;
    }

    @Bean
    public FileStorageGateway fileStorageGateway() {
        return fileStorageFactory.fileStorageGateway();
    }
}
