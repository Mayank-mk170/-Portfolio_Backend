package com.portfolio.controller;

import com.portfolio.dto.UserProfileRequest;
import com.portfolio.entity.UserProfile;
import com.portfolio.service.UserProfileService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-profile")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;


    // ==========================================
    // PUBLIC - GET PROFILE
    // ==========================================

    @GetMapping
    public ResponseEntity<UserProfile> getProfile() {

        UserProfile profile =
                userProfileService.getProfile();

        if (profile == null) {

            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(profile);
    }


    // ==========================================
    // ADMIN - SAVE PROFILE
    // ==========================================

    @PostMapping
    public ResponseEntity<UserProfile> saveProfile(
            @Valid @RequestBody
            UserProfileRequest request
    ) {

        return ResponseEntity.ok(
                userProfileService.saveProfile(request)
        );
    }
}