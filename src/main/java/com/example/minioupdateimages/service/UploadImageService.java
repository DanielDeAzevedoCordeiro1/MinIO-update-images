package com.example.minioupdateimages.service;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class UploadImageService {

    private final MinioClient minioClient;
    private final JdbcClient jdbcClient;

    public UploadImageService(MinioClient minioClient, JdbcClient jdbcClient) {
        this.minioClient = minioClient;
        this.jdbcClient = jdbcClient;
    }

    public void uploadImage(MultipartFile file) throws Exception {
        var inputStream = file.getInputStream();
        var objectId = UUID.randomUUID().toString();
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket("images")
                        .object(objectId)
                        .stream(inputStream, inputStream.available(), -1)
                        .contentType("image/png")
                        .build());

        jdbcClient.sql("""
                        INSERT INTO images (object_id) VALUES (:objectId)
                        """)
                .param(objectId)
                .update();
    }
}
