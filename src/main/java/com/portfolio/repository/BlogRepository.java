package com.portfolio.repository;

import com.portfolio.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BlogRepository
        extends JpaRepository<Blog, Long> {

    Optional<Blog> findBySlug(String slug);

    List<Blog> findAllByOrderByCreatedAtDesc();
}