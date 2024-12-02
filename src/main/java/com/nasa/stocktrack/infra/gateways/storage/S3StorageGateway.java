package com.nasa.stocktrack.infra.gateways.storage;

import com.nasa.stocktrack.application.gateways.FileStorageGateway;
import com.nasa.stocktrack.domain.entities.RecoveredFile;
import com.nasa.stocktrack.infra.config.storage.FileStorageProperties;
import com.nasa.stocktrack.infra.exceptions.FileNotFoundException;
import com.nasa.stocktrack.infra.exceptions.FileStorageException;
import com.nasa.stocktrack.infra.utis.TikaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.*;

public class S3StorageGateway implements FileStorageGateway {

    private final String bucketName;

    @Autowired
    private S3Client s3Client;

    public S3StorageGateway(FileStorageProperties fileStorageProperties) {
        this.bucketName = fileStorageProperties.getS3().getBucketName();
    }

    @Override
    public RecoveredFile getFile(String filename) {
        try {
            HeadObjectRequest objectRequest = HeadObjectRequest.builder()
                    .bucket(bucketName)
                    .key(filename)
                    .build();

            s3Client.headObject(objectRequest);

            GetUrlRequest getUrlRequest = GetUrlRequest.builder()
                    .bucket(bucketName)
                    .key(filename)
                    .build();


            String url = s3Client.utilities().getUrl(getUrlRequest).toString();

            return new RecoveredFile(url);
        } catch (S3Exception e) {
            if(e.statusCode() == 404) {
                throw new FileNotFoundException(filename);
            }

            throw new FileStorageException("File cannot be recovered");
        }
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
