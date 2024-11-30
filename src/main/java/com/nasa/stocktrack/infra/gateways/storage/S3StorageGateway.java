package com.nasa.stocktrack.infra.gateways.storage;

import com.nasa.stocktrack.application.gateways.FileStorageGateway;
import com.nasa.stocktrack.infra.config.storage.FileStorageProperties;
import com.nasa.stocktrack.infra.exceptions.FileStorageException;
import com.nasa.stocktrack.infra.utis.TikaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.*;

public class S3StorageGateway implements FileStorageGateway {

    private final String bucketName;

    @Autowired
    private S3Client s3Client;

    public S3StorageGateway(FileStorageProperties fileStorageProperties) {
        this.bucketName = fileStorageProperties.getS3().getBucketName();
    }

    @Override
    public InputStream getFile(String filename) {
        return null;
    }

    @Override
    public void saveFile(InputStream content, String filename) {
        try {
            byte[] fileBytes = content.readAllBytes();

            String mineType = TikaUtils.getMimeType(fileBytes);

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(filename)
                    .contentType(mineType)
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(fileBytes));
        } catch (Exception e) {
            throw new FileStorageException("Error storing file "
                    + filename
                    + ", please try again"
            );
        }
    }

    @Override
    public void deleteFile(String filename) {

    }
}
