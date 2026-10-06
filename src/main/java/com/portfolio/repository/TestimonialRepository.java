package com.portfolio.repository;

import com.portfolio.entity.Testimonial;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestimonialRepository
        extends JpaRepository<Testimonial, Long> {
}