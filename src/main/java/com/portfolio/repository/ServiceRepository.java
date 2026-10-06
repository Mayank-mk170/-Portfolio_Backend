package com.portfolio.repository;

import com.portfolio.entity.PortfolioService;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository
        extends JpaRepository<PortfolioService, Long> {
}