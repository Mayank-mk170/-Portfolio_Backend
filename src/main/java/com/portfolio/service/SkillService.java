package com.portfolio.service;

import com.portfolio.dto.SkillRequest;
import com.portfolio.entity.Skill;
import com.portfolio.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;


    // =========================
    // CREATE
    // =========================
    public Skill createSkill(SkillRequest request) {

        Skill skill = new Skill();

        skill.setName(request.getName());
        skill.setCategory(request.getCategory());
        skill.setIcon(request.getIcon());
        skill.setProficiency(request.getProficiency());
        skill.setDisplayOrder(request.getDisplayOrder());

        return skillRepository.save(skill);
    }


    // =========================
    // GET ALL
    // =========================
    public List<Skill> getAllSkills() {

        return skillRepository.findAllByOrderByDisplayOrderAsc();
    }


    // =========================
    // GET BY ID
    // =========================
    public Skill getSkillById(Long id) {

        return skillRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Skill not found")
                );
    }


    // =========================
    // UPDATE
    // =========================
    public Skill updateSkill(Long id, SkillRequest request) {

        Skill skill = skillRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Skill not found")
                );

        skill.setName(request.getName());
        skill.setCategory(request.getCategory());
        skill.setIcon(request.getIcon());
        skill.setProficiency(request.getProficiency());
        skill.setDisplayOrder(request.getDisplayOrder());

        return skillRepository.save(skill);
    }


    // =========================
    // DELETE
    // =========================
    public void deleteSkill(Long id) {

        Skill skill = skillRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Skill not found")
                );

        skillRepository.delete(skill);
    }
}