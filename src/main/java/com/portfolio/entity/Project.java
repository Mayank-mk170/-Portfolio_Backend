package com.portfolio.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, length = 150)
    private String title;


    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(length = 500)
    private String imageUrl;

    @Column(length = 500)
    private String githubUrl;

    @Column(length = 500)
    private String liveUrl;

    @Column(length = 500)
    private String technologies;

    @Column(nullable = false)
    private Boolean featured;

    @Column(nullable = false)
    private Integer displayOrder;

}