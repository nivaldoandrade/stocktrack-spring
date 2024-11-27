package com.nasa.stocktrack.infra.gateways.storage;

import com.nasa.stocktrack.application.gateways.FileStorageGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.*;

@Component
public class S3StorageGateway implements FileStorageGateway {

    @Value("${storage.s3.bucket-name}")
    private String bucketName;

    private final S3Client s3Client;

    public S3StorageGateway(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public InputStream getFile(String filename) {
        return null;
    }

    @Override
    public void saveFile(InputStream content, String filename) {
        System.out.println(bucketName);
        System.out.println("SaveFile: S3");
    }

    @Override
    public void deleteFile(String filename) {

    }
}
