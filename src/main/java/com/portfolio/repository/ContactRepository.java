package com.portfolio.repository;

import com.portfolio.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository
        extends JpaRepository<Contact, Long> {

    List<Contact> findAllByOrderByCreatedAtDesc();
}