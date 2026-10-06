package com.portfolio.controller;

import com.portfolio.dto.ProjectRequest;
import com.portfolio.entity.Project;
import com.portfolio.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;


    //create
    @PostMapping
    public ResponseEntity<?> createProject(
            @Valid @RequestBody ProjectRequest request
            ){
        Project project = projectService.createProject(request);
        return new ResponseEntity<>("Project created successful", HttpStatus.CREATED);
    }

    // Get
    @GetMapping
    public ResponseEntity<?> getAllProject(){
        return ResponseEntity.ok(projectService.getAllProject());
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProject(
            @PathVariable Long id,
            @Valid @RequestBody ProjectRequest request
    ) {

        try {

            Project project =
                    projectService.updateProject(
                            id,
                            request
                    );

            return ResponseEntity.ok(project);

        } catch (RuntimeException e) {

            return new ResponseEntity<>("Project not found",HttpStatus.OK);
        }
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(
            @PathVariable Long id
    ) {
        try {

            projectService.deleteProject(id);

            return new ResponseEntity<>("Project deleted successfully",HttpStatus.OK);

        } catch (RuntimeException e) {

            return new ResponseEntity<>("Project not found", HttpStatus.OK);
        }
    }

}
