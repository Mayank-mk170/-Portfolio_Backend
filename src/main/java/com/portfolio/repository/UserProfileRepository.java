package com.portfolio.repository;

import com.portfolio.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository
        extends JpaRepository<UserProfile, Long> {
}