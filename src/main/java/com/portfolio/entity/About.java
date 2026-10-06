package com.portfolio.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "about")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class About {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "heading", nullable = false)
    private String heading;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "short_description", columnDefinition = "TEXT")
    private String shortDescription;

    @Column(name = "profile_image", length = 500)
    private String profileImage;

    @Column(name = "resume_url", length = 500)
    private String resumeUrl;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(length = 255)
    private String heroEyebrow;

    @Column(name = "hero_title_line_1", length = 255)
    private String heroTitleLine1;

    @Column(name = "hero_title_line_2", length = 255)
    private String heroTitleLine2;

    @Column(name = "hero_title_line_3", length = 255)
    private String heroTitleLine3;

    @Column(name = "hero_description", columnDefinition = "TEXT")
    private String heroDescription;

    @Column(name = "hero_image", length = 500)
    private String heroImage;

}