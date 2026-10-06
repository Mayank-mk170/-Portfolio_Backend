package com.portfolio.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectRequest {

    @NotBlank(message = "Project title is required")
    private String title;

    @NotBlank(message = "Project description is required")
    private String description;

    private String imageUrl;

    private String githubUrl;

    private String liveUrl;

    private String technologies;

    private Boolean featured = false;

    @Min(0)
    private Integer displayOrder = 0;
}