package com.portfolio.service;

import com.portfolio.dto.ProjectRequest;
import com.portfolio.entity.Project;
import com.portfolio.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    // create
    public Project createProject(
            ProjectRequest request
    ){
        Project project = new Project();

        project.setTitle(request.getTitle());

        project.setDescription(request.getDescription());

        project.setImageUrl(request.getImageUrl());

        project.setGithubUrl(request.getGithubUrl());

        project.setLiveUrl(request.getLiveUrl());

        project.setTechnologies(request.getTechnologies());

        project.setFeatured(request.getFeatured() != null
                        ? request.getFeatured()
                        : false);

        project.setDisplayOrder(request.getDisplayOrder() != null
                        ? request.getDisplayOrder()
                        : 0);
        return projectRepository.save(project);
    }

    // Get

    public List<?> getAllProject(){
        return projectRepository.findAllByOrderByDisplayOrderAsc();
    }

    // Update
    public Project updateProject(
            Long id, ProjectRequest request
    ) {

        Project project = projectRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Project not found" ));

        project.setTitle(request.getTitle());

        project.setDescription(request.getDescription());

        project.setImageUrl(request.getImageUrl());

        project.setGithubUrl(request.getGithubUrl());

        project.setLiveUrl(request.getLiveUrl());

        project.setTechnologies(request.getTechnologies());

        project.setFeatured(
                request.getFeatured() != null
                        ? request.getFeatured()
                        : false
        );

        project.setDisplayOrder(
                request.getDisplayOrder() != null
                        ? request.getDisplayOrder()
                        : 0
        );

        return projectRepository.save(project);
    }

    // Delete

    public void deleteProject(Long id){
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        projectRepository.delete(project);
    }


}
