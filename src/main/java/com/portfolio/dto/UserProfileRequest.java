package com.portfolio.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileRequest {

    @NotBlank(message = "Name is required")
    private String name;

    private String email;

    private String education;
}