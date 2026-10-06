package com.portfolio.service;

import com.portfolio.dto.AboutRequest;
import com.portfolio.entity.About;
import com.portfolio.repository.AboutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AboutService {

    private final AboutRepository aboutRepository;

    // ==========================================
    // CREATE
    // ==========================================

    public About createAbout(AboutRequest request) {

        if (aboutRepository.count() > 0) {
            throw new IllegalStateException(
                    "About section already exists"
            );
        }

        About about = new About();

        // ==========================================
        // ABOUT CONTENT
        // ==========================================

        about.setHeading(
                request.getHeading()
        );

        about.setDescription(
                request.getDescription()
        );

        about.setShortDescription(
                request.getShortDescription()
        );

        about.setProfileImage(
                request.getProfileImage()
        );

        about.setResumeUrl(
                request.getResumeUrl()
        );

        // ==========================================
        // HERO CONTENT
        // ==========================================

        about.setHeroEyebrow(
                request.getHeroEyebrow()
        );

        about.setHeroTitleLine1(
                request.getHeroTitleLine1()
        );

        about.setHeroTitleLine2(
                request.getHeroTitleLine2()
        );

        about.setHeroTitleLine3(
                request.getHeroTitleLine3()
        );

        about.setHeroDescription(
                request.getHeroDescription()
        );

        // ==========================================
        // UPDATED TIME
        // ==========================================

        about.setUpdatedAt(
                LocalDateTime.now()
        );

        return aboutRepository.save(about);
    }


    // ==========================================
    // GET
    // ==========================================

    public About getAbout() {

        return aboutRepository.findAll()
                .stream()
                .findFirst()
                .orElse(null);
    }


    // ==========================================
    // UPDATE
    // ==========================================

    public About updateAbout(
            Long id,
            AboutRequest request
    ) {

        About about = aboutRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "About section not found"
                        )
                );

        // ==========================================
        // ABOUT CONTENT
        // ==========================================

        about.setHeading(
                request.getHeading()
        );

        about.setDescription(
                request.getDescription()
        );

        about.setShortDescription(
                request.getShortDescription()
        );

        about.setProfileImage(
                request.getProfileImage()
        );

        about.setResumeUrl(
                request.getResumeUrl()
        );

        // ==========================================
        // HERO CONTENT
        // ==========================================

        about.setHeroEyebrow(
                request.getHeroEyebrow()
        );

        about.setHeroTitleLine1(
                request.getHeroTitleLine1()
        );

        about.setHeroTitleLine2(
                request.getHeroTitleLine2()
        );

        about.setHeroTitleLine3(
                request.getHeroTitleLine3()
        );

        about.setHeroDescription(
                request.getHeroDescription()
        );
        about.setHeroImage(
                request.getHeroImage()
        );
        // ==========================================
        // UPDATED TIME
        // ==========================================

        about.setUpdatedAt(
                LocalDateTime.now()
        );

        return aboutRepository.save(about);
    }


    // ==========================================
    // DELETE
    // ==========================================

    public void deleteAbout(Long id) {

        About about = aboutRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "About section not found"
                        )
                );

        aboutRepository.delete(about);
    }
}