package com.portfolio.service;

import com.portfolio.dto.UserProfileRequest;
import com.portfolio.entity.UserProfile;
import com.portfolio.repository.UserProfileRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;


    public UserProfile getProfile() {

        return userProfileRepository
                .findAll()
                .stream()
                .findFirst()
                .orElse(null);
    }


    public UserProfile saveProfile(
            UserProfileRequest request
    ) {

        UserProfile profile = getProfile();

        if (profile == null) {

            profile = new UserProfile();
        }

        profile.setName(request.getName());
        profile.setEmail(request.getEmail());
        profile.setEducation(request.getEducation());

        return userProfileRepository.save(profile);
    }
}