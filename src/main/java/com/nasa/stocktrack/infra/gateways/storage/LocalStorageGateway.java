package com.nasa.stocktrack.infra.gateways.storage;

import com.nasa.stocktrack.application.gateways.FileStorageGateway;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Component
public class LocalStorageGateway implements FileStorageGateway {

    private final Path fileStorageLocation;

    public LocalStorageGateway() {
        this.fileStorageLocation = Paths.get("uploads").toAbsolutePath().normalize();
    }

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(fileStorageLocation);
        } catch (Exception e) {
            throw new RuntimeException("Error creating folder where files will be stored");
        }
    }

    @Override
    public void saveFile(InputStream content, String filename) {
        Path targetLocation = this.fileStorageLocation.resolve(filename);

        try(InputStream inputStream = content) {
            Files.copy(inputStream, targetLocation);
        } catch (IOException e) {
            throw new RuntimeException("Error storing file "
                    + filename
                    + ", please try again"
            );
        }
    }
}
