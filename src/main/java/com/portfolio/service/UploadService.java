package com.portfolio.service;

import com.portfolio.entity.About;
import com.portfolio.entity.Upload;
import com.portfolio.repository.AboutRepository;
import com.portfolio.repository.UploadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UploadService {

    private final S3Client s3Client;
    private final UploadRepository uploadRepository;
    private final AboutRepository aboutRepository;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    @Value("${aws.s3.region}")
    private String region;

    public Upload uploadFile(
            MultipartFile file,
            String category
    ) throws IOException {

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File cannot be empty");
        }

        String contentType = file.getContentType();

        if (contentType == null) {
            throw new IllegalArgumentException(
                    "File type could not be detected"
            );
        }

        boolean isImage = contentType.startsWith("image/");
        boolean isPdf = contentType.equals("application/pdf");

        if (!isImage && !isPdf) {
            throw new IllegalArgumentException(
                    "Only images and PDF files are allowed"
            );
        }

        if (category == null || category.trim().isEmpty()) {
            category = "OTHER";
        }

        category = category.trim().toUpperCase();

        String originalFileName = file.getOriginalFilename();

        if (originalFileName == null ||
                originalFileName.trim().isEmpty()) {

            originalFileName = "file";
        }

        String extension = "";

        if (originalFileName.contains(".")) {
            extension = originalFileName.substring(
                    originalFileName.lastIndexOf(".")
            );
        }

        String fileKey =
                "portfolio/"
                        + category.toLowerCase()
                        + "/"
                        + UUID.randomUUID()
                        + extension;

        PutObjectRequest request =
                PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(fileKey)
                        .contentType(contentType)
                        .contentLength(file.getSize())
                        .build();

        s3Client.putObject(
                request,
                RequestBody.fromBytes(file.getBytes())
        );

        String fileUrl =
                "https://"
                        + bucketName
                        + ".s3."
                        + region
                        + ".amazonaws.com/"
                        + fileKey;

        Upload upload = Upload.builder()
                .originalFileName(originalFileName)
                .fileUrl(fileUrl)
                .s3Key(fileKey)
                .contentType(contentType)
                .fileSize(file.getSize())
                .category(category)
                .build();

        Upload savedUpload = uploadRepository.save(upload);

        /*
         * HERO IMAGE
         *
         * When an upload is made with category HERO,
         * automatically save its S3 URL into About.heroImage.
         */
        if ("HERO".equals(category)) {

            About about = aboutRepository.findAll()
                    .stream()
                    .findFirst()
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "About section not found. Please create About section first."
                            )
                    );

            about.setHeroImage(fileUrl);

            aboutRepository.save(about);
        }

        return savedUpload;
    }

    public List<Upload> getAllUploads() {
        return uploadRepository.findAllByOrderByCreatedAtDesc();
    }
}