package com.nasa.stocktrack.infra.gateways.storage;

import com.nasa.stocktrack.application.gateways.FileStorageGateway;
import com.nasa.stocktrack.infra.config.storage.FileStorageProperties;
import com.nasa.stocktrack.infra.exceptions.FileNotFoundException;
import com.nasa.stocktrack.infra.exceptions.FileStorageException;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;


public class LocalStorageGateway implements FileStorageGateway {

    private final Path fileStorageLocation;

    public LocalStorageGateway(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = fileStorageProperties.getLocalStorageLocation();
    }

    @Override
    public InputStream getFile(String filename) {
        try {
            Path targetLocation = this.fileStorageLocation.resolve(filename);

            if(!Files.exists(targetLocation)) {
                throw new FileNotFoundException(filename);
            }

            return new FileInputStream(targetLocation.toFile());
        } catch (IOException e) {
            throw new FileStorageException("File cannot be recovered");
        }

    }

    @Override
    public void saveFile(InputStream content, String filename) {
        Path targetLocation = this.fileStorageLocation.resolve(filename);

        try(InputStream inputStream = content) {
            Files.copy(inputStream, targetLocation);
        } catch (IOException e) {
            throw new FileStorageException("Error storing file "
                    + filename
                    + ", please try again"
            );
        }
    }

    @Override
    public void deleteFile(String filename) {
        try {
            Path targetLocation = this.fileStorageLocation.resolve(filename);

            Files.deleteIfExists(targetLocation);
        } catch (IOException e) {
            throw new FileStorageException("Error when deletion file");
        }
    }
}
