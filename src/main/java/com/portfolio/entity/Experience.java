package com.portfolio.entity;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "experience")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String company;

    @Column(nullable = false, length = 255)
    private String position;

    @Column(columnDefinition = "TEXT")
    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private Boolean currentlyWorking = false;

    @Column(name = "\"order\"")
    private Integer order;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

    @PrePersist
    protected void onCreate() {

        createdAt = OffsetDateTime.now();
        updatedAt = OffsetDateTime.now();

        if (currentlyWorking == null) {
            currentlyWorking = false;
        }
    }

    @PreUpdate
    protected void onUpdate() {

        updatedAt = OffsetDateTime.now();
    }
}