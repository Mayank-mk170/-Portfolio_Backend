package com.portfolio.service;

import com.portfolio.dto.ExperienceRequest;
import com.portfolio.entity.Experience;
import com.portfolio.repository.ExperienceRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExperienceService {

    private final ExperienceRepository experienceRepository;

    // CREATE
    public Experience createExperience(
            ExperienceRequest request
    ) {

        Experience experience = new Experience();

        experience.setCompany(request.getCompany());
        experience.setPosition(request.getPosition());
        experience.setDescription(request.getDescription());
        experience.setStartDate(request.getStartDate());
        experience.setEndDate(request.getEndDate());

        experience.setCurrentlyWorking(
                request.getCurrentlyWorking() != null
                        ? request.getCurrentlyWorking()
                        : false
        );

        experience.setOrder(request.getOrder());

        return experienceRepository.save(experience);
    }

    // READ ALL
    public List<Experience> getAllExperiences() {

        return experienceRepository.findAll();
    }

    // READ ONE
    public Experience getExperienceById(Long id) {

        return experienceRepository
                .findById(id)
                .orElse(null);
    }

    // UPDATE
    public Experience updateExperience(
            Long id,
            ExperienceRequest request
    ) {

        Experience experience =
                experienceRepository
                        .findById(id)
                        .orElse(null);

        if (experience == null) {
            return null;
        }

        experience.setCompany(request.getCompany());
        experience.setPosition(request.getPosition());
        experience.setDescription(request.getDescription());
        experience.setStartDate(request.getStartDate());
        experience.setEndDate(request.getEndDate());

        experience.setCurrentlyWorking(
                request.getCurrentlyWorking() != null
                        ? request.getCurrentlyWorking()
                        : false
        );

        experience.setOrder(request.getOrder());

        return experienceRepository.save(experience);
    }

    // DELETE
    public boolean deleteExperience(Long id) {

        Experience experience =
                experienceRepository
                        .findById(id)
                        .orElse(null);

        if (experience == null) {
            return false;
        }

        experienceRepository.delete(experience);

        return true;
    }
}