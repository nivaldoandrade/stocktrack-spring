package com.nasa.stocktrack.infra.config.storage;

import com.nasa.stocktrack.infra.exceptions.FileStorageException;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "storage")
public class FileStorageProperties {

    private StorageType type;

    private Local local = new Local();

    private S3 s3 = new S3();

    private Path localStorageLocation;

    public enum StorageType {
        LOCAL, S3
    }

    @Getter
    @Setter
    public class Local {
        private String uploadDir;
    }

    @Getter
    @Setter
    public class S3 {
        private String bucketName;
        private String region;
    }

    @PostConstruct
    private void init() {
        if (type == null) {
            throw new IllegalArgumentException(
                    "storage.type must not be null and must be one of: "
                    + Arrays.toString(StorageType.values())
                    + ". Check application.properties!"
            );
        }

        switch (type) {
            case LOCAL -> createLocalDir();
            case S3 -> checkEnvS3();
        }
    }

    private void createLocalDir() {
        Path uploadLocalDirPath = Paths.get(local.getUploadDir()).toAbsolutePath().normalize();

        try {
            Files.createDirectories(uploadLocalDirPath);

            this.localStorageLocation = uploadLocalDirPath;
        } catch(Exception e) {
            throw new FileStorageException("Error creating local folder where files will be stored");
        }
    }

    private void checkEnvS3() {
        //Implement logic check env S3
    }
}
