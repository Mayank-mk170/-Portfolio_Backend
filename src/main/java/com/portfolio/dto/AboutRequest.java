package com.portfolio.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AboutRequest {

    @NotBlank(message = "Heading is required")
    private String heading;

    @NotBlank(message = "Description is required")
    private String description;

    private String shortDescription;

    private String profileImage;

    private String resumeUrl;

    private String heroEyebrow;

    private String heroTitleLine1;

    private String heroTitleLine2;

    private String heroTitleLine3;

    private String heroDescription;

    private String heroImage;
}
