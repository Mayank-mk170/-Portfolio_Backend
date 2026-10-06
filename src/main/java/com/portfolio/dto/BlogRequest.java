package com.portfolio.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BlogRequest {

    @NotBlank(message = "Blog title is required")
    private String title;

    @NotBlank(message = "Blog slug is required")
    private String slug;

    @NotBlank(message = "Blog content is required")
    private String content;

    private String excerpt;

    private String image;

    private String author;

    private List<String> tags;

    private Boolean published;
}