package com.portfolio.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestimonialRequest {

    @NotBlank(message = "Name is required")
    private String name;

    private String position;

    private String company;

    @NotBlank(message = "Content is required")
    private String content;

    private String image;

    @Min(1)
    @Max(5)
    private Integer rating;

    private Boolean featured;

    private Integer order;
}