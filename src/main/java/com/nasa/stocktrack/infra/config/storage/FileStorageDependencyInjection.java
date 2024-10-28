package com.nasa.stocktrack.infra.config.storage;

import com.nasa.stocktrack.application.gateways.FileStorageGateway;
import com.nasa.stocktrack.application.services.FileStorageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileStorageDependencyInjection {

    @Bean
    FileStorageService FileStorageService(FileStorageGateway fileStorageGateway) {
        return new FileStorageService(fileStorageGateway);
    }

}
