package com.portfolio.controller;

import com.portfolio.dto.TestimonialRequest;
import com.portfolio.entity.Testimonial;
import com.portfolio.service.TestimonialService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/testimonials")
@RequiredArgsConstructor
public class TestimonialController {

    private final TestimonialService testimonialService;

    // CREATE
    @PostMapping
    public ResponseEntity<?> createTestimonial(
            @Valid @RequestBody TestimonialRequest request
    ) {

        Testimonial testimonial =
                testimonialService.createTestimonial(request);

        return new ResponseEntity<>(
                testimonial,
                HttpStatus.CREATED
        );
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<Testimonial>> getAllTestimonials() {

        return new ResponseEntity<>(
                testimonialService.getAllTestimonials(),
                HttpStatus.OK
        );
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<?> getTestimonial(
            @PathVariable Long id
    ) {

        Testimonial testimonial =
                testimonialService.getTestimonialById(id);

        if (testimonial == null) {

            return new ResponseEntity<>(
                    "Testimonial not found",
                    HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<>(
                testimonial,
                HttpStatus.OK
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTestimonial(
            @PathVariable Long id,
            @Valid @RequestBody TestimonialRequest request
    ) {

        Testimonial testimonial =
                testimonialService.updateTestimonial(
                        id,
                        request
                );

        if (testimonial == null) {

            return new ResponseEntity<>(
                    "Testimonial not found",
                    HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<>(
                testimonial,
                HttpStatus.OK
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTestimonial(
            @PathVariable Long id
    ) {

        boolean deleted =
                testimonialService.deleteTestimonial(id);

        if (!deleted) {

            return new ResponseEntity<>(
                    "Testimonial not found",
                    HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<>(
                "Testimonial deleted successfully",
                HttpStatus.OK
        );
    }
}