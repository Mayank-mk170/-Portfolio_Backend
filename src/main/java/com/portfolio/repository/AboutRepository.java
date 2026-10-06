package com.portfolio.repository;

import com.portfolio.entity.About;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AboutRepository extends JpaRepository<About, Long> {
}