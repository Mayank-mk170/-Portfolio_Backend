package com.portfolio.controller;

import com.portfolio.dto.AboutRequest;
import com.portfolio.entity.About;
import com.portfolio.service.AboutService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/about")
@RequiredArgsConstructor
public class AboutController {

    private final AboutService aboutService;


    // ==========================================
    // CREATE
    // ADMIN ONLY
    // ==========================================

    @PostMapping
    public ResponseEntity<?> createAbout(
            @Valid @RequestBody AboutRequest request
    ) {

        try {

            About about =
                    aboutService.createAbout(request);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(about);

        } catch (IllegalStateException e) {

            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(
                            Map.of(
                                    "message",
                                    "About section already exists"
                            )
                    );
        }
    }


    // ==========================================
    // GET
    // PUBLIC
    // ==========================================

    @GetMapping
    public ResponseEntity<?> getAbout() {

        About about =
                aboutService.getAbout();

        if (about == null) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(
                            Map.of(
                                    "message",
                                    "About section not found"
                            )
                    );
        }

        return ResponseEntity.ok(about);
    }


    // ==========================================
    // UPDATE
    // ADMIN ONLY
    // ==========================================

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAbout(
            @PathVariable Long id,
            @Valid @RequestBody AboutRequest request
    ) {

        try {

            About about =
                    aboutService.updateAbout(
                            id,
                            request
                    );

            return ResponseEntity.ok(about);

        } catch (RuntimeException e) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(
                            Map.of(
                                    "message",
                                    "About section not found"
                            )
                    );
        }
    }


    // ==========================================
    // DELETE
    // ADMIN ONLY
    // ==========================================

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAbout(
            @PathVariable Long id
    ) {

        try {

            aboutService.deleteAbout(id);

            return ResponseEntity.ok(
                    Map.of(
                            "message",
                            "About section deleted successfully"
                    )
            );

        } catch (RuntimeException e) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(
                            Map.of(
                                    "message",
                                    "About section not found"
                            )
                    );
        }
    }
}