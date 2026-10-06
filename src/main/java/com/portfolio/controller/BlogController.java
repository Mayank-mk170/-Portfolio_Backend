package com.portfolio.controller;

import com.portfolio.dto.BlogRequest;
import com.portfolio.entity.Blog;
import com.portfolio.service.BlogService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    // CREATE
    @PostMapping
    public ResponseEntity<?> createBlog(
            @Valid @RequestBody BlogRequest request
    ) {

        Blog blog = blogService.createBlog(request);

        return new ResponseEntity<>(
                blog,
                HttpStatus.CREATED
        );
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<Blog>> getAllBlogs() {

        return new ResponseEntity<>(
                blogService.getAllBlogs(),
                HttpStatus.OK
        );
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<?> getBlog(
            @PathVariable Long id
    ) {

        Blog blog = blogService.getBlogById(id);

        if (blog == null) {

            return new ResponseEntity<>(
                    "Blog not found",
                    HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<>(
                blog,
                HttpStatus.OK
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBlog(
            @PathVariable Long id,
            @Valid @RequestBody BlogRequest request
    ) {

        Blog blog = blogService.updateBlog(id, request);

        if (blog == null) {

            return new ResponseEntity<>(
                    "Blog not found",
                    HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<>(
                blog,
                HttpStatus.OK
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBlog(
            @PathVariable Long id
    ) {

        boolean deleted = blogService.deleteBlog(id);

        if (!deleted) {

            return new ResponseEntity<>(
                    "Blog not found",
                    HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<>(
                "Blog deleted successfully",
                HttpStatus.OK
        );
    }
}