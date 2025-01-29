package com.example.minioupdateimages.controller;

import com.example.minioupdateimages.service.UploadImageService;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("images")
public class UpdateImageController {

    private final UploadImageService uploadImageService;
    private final MinioClient minioClient;

    public UpdateImageController(UploadImageService uploadImageService, MinioClient minioClient) {
        this.uploadImageService = uploadImageService;
        this.minioClient = minioClient;
    }

    @PostMapping
    public ResponseEntity<Void> execute(@RequestParam MultipartFile file) throws Exception {
        uploadImageService.uploadImage(file);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{objectId}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage(@PathVariable String objectId) throws Exception {
        var stream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket("images").object(objectId)
                        .build());
                return IOUtils.toByteArray(stream);
    }
}
