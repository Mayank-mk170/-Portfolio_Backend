package com.portfolio.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillRequest {

    @NotBlank(message = "Skill name is required")
    private String name;

    private String category;

    private String icon;

    @Min(value = 0, message = "Proficiency cannot be less than 0")
    @Max(value = 100, message = "Proficiency cannot be greater than 100")
    private Integer proficiency;

    private Integer displayOrder;
}