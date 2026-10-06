package com.portfolio.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ExperienceRequest {

    @NotBlank(message = "Company is required")
    private String company;

    @NotBlank(message = "Position is required")
    private String position;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private Boolean currentlyWorking;

    private Integer order;
}