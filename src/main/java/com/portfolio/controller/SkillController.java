package com.portfolio.controller;

import com.portfolio.dto.SkillRequest;
import com.portfolio.entity.Skill;
import com.portfolio.service.SkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
public class SkillController {

    private final SkillService skillService;


    // =========================
    // CREATE
    // =========================
    @PostMapping
    public ResponseEntity<?> createSkill(
            @Valid @RequestBody SkillRequest request) {

        Skill skill = skillService.createSkill(request);

        return new ResponseEntity<>("Skill created successfully", HttpStatus.CREATED);
    }


    // =========================
    // GET ALL
    // =========================
    @GetMapping
    public ResponseEntity<List<Skill>> getAllSkills() {

        return ResponseEntity.ok(
                skillService.getAllSkills()
        );
    }


    // =========================
    // GET BY ID
    // =========================
    @GetMapping("/{id}")
    public ResponseEntity<?> getSkillById(
            @PathVariable Long id) {

        try {

            return ResponseEntity.ok(
                    skillService.getSkillById(id)
            );

        } catch (RuntimeException e) {

            return new ResponseEntity<>("Skill not found", HttpStatus.NOT_FOUND);
        }
    }


    // =========================
    // UPDATE
    // =========================
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSkill(
            @PathVariable Long id,
            @Valid @RequestBody SkillRequest request) {

        try {

            return ResponseEntity.ok(
                    skillService.updateSkill(id, request)
            );

        } catch (RuntimeException e) {

            return new ResponseEntity<>("Skill updated successful", HttpStatus.OK);
        }
    }


    // =========================
    // DELETE
    // =========================
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSkill(
            @PathVariable Long id) {

            skillService.deleteSkill(id);
            return new ResponseEntity<>("Skill Delete successful", HttpStatus.OK);

    }
}