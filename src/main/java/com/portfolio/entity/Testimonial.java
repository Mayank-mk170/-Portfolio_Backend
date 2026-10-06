package com.portfolio.entity;

import jakarta.persistence.*;

import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "testimonials")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Testimonial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(length = 255)
    private String position;

    @Column(length = 255)
    private String company;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(length = 500)
    private String image;

    private Integer rating;

    private Boolean featured = false;

    @Column(name = "\"order\"")
    private Integer order;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

    @PrePersist
    protected void onCreate() {

        createdAt = OffsetDateTime.now();
        updatedAt = OffsetDateTime.now();

        if (featured == null) {
            featured = false;
        }
    }

    @PreUpdate
    protected void onUpdate() {

        updatedAt = OffsetDateTime.now();
    }
}