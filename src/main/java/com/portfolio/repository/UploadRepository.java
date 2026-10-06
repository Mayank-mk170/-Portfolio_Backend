package com.portfolio.repository;

import com.portfolio.entity.Upload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UploadRepository extends JpaRepository<Upload, Long> {
    List<Upload> findAllByOrderByCreatedAtDesc();
}