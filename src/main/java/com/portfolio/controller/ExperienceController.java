package com.portfolio.controller;

import com.portfolio.dto.ExperienceRequest;
import com.portfolio.entity.Experience;
import com.portfolio.service.ExperienceService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experience")
@RequiredArgsConstructor
public class ExperienceController {

    private final ExperienceService experienceService;

    // CREATE
    @PostMapping
    public ResponseEntity<?> createExperience(
            @Valid @RequestBody ExperienceRequest request
    ) {

        Experience experience =
                experienceService.createExperience(request);

        return new ResponseEntity<>(
                experience,
                HttpStatus.CREATED
        );
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<Experience>> getAllExperiences() {

        return new ResponseEntity<>(
                experienceService.getAllExperiences(),
                HttpStatus.OK
        );
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<?> getExperience(
            @PathVariable Long id
    ) {

        Experience experience =
                experienceService.getExperienceById(id);

        if (experience == null) {

            return new ResponseEntity<>(
                    "Experience not found",
                    HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<>(
                experience,
                HttpStatus.OK
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> updateExperience(
            @PathVariable Long id,
            @Valid @RequestBody ExperienceRequest request
    ) {

        Experience experience =
                experienceService.updateExperience(
                        id,
                        request
                );

        if (experience == null) {

            return new ResponseEntity<>(
                    "Experience not found",
                    HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<>(
                experience,
                HttpStatus.OK
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExperience(
            @PathVariable Long id
    ) {

        boolean deleted =
                experienceService.deleteExperience(id);

        if (!deleted) {

            return new ResponseEntity<>(
                    "Experience not found",
                    HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<>(
                "Experience deleted successfully",
                HttpStatus.OK
        );
    }
}