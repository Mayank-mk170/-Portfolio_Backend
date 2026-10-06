package com.portfolio.controller;


import com.portfolio.entity.Upload;
import com.portfolio.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/uploads")
@RequiredArgsConstructor
public class UploadController {

    private final UploadService uploadService;

    // ==========================================
    // UPLOAD FILE
    // ==========================================

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam(
                    value = "category",
                    defaultValue = "OTHER"
            )
            String category
    ) {

        try {

            Upload upload =
                    uploadService.uploadFile(
                            file,
                            category
                    );

            return ResponseEntity.ok(upload);

        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest()
                    .body(
                            Map.of(
                                    "message",
                                    e.getMessage()
                            )
                    );

        } catch (Exception e) {

            return ResponseEntity.internalServerError()
                    .body(
                            Map.of(
                                    "message",
                                    "File upload failed"
                            )
                    );
        }
    }


    // ==========================================
    // GET ALL UPLOADS
    // ==========================================

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Upload>> getAllUploads() {

        return ResponseEntity.ok(
                uploadService.getAllUploads()
        );
    }
}