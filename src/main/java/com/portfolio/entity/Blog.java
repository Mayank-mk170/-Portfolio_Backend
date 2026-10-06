package com.portfolio.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "blogs")

public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(
            name = "title",
            nullable = false,
            length = 255
    )
    private String title;

    @Column(
            name = "slug",
            nullable = false,
            unique = true,
            length = 255
    )
    private String slug;

    @Column(
            name = "content",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String content;

    @Column(
            name = "excerpt",
            length = 1000
    )
    private String excerpt;

    @Column(
            name = "image",
            length = 500
    )
    private String image;

    @Column(length = 255)
    @Builder.Default
    private String author = "Admin";

    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(
            name = "tags",
            columnDefinition = "text[]"
    )
    @Builder.Default
    private List<String> tags = new ArrayList<>();

    @Column(nullable = false)
    @Builder.Default
    private Boolean published = false;

    @Column(name = "published_at")
    private OffsetDateTime publishedAt;

    @Column(nullable = false)
    @Builder.Default
    private Integer views = 0;

    @Column(
            name = "created_at",
            nullable = false,
            updatable = false
    )
    @Builder.Default
    private OffsetDateTime createdAt =
            OffsetDateTime.now();

    @Column(
            name = "updated_at",
            nullable = false
    )
    @Builder.Default
    private OffsetDateTime updatedAt =
            OffsetDateTime.now();

    @PrePersist
    protected void onCreate() {

        OffsetDateTime now =
                OffsetDateTime.now();

        if (createdAt == null) {
            createdAt = now;
        }

        if (updatedAt == null) {
            updatedAt = now;
        }

        if (author == null ||
                author.trim().isEmpty()) {
            author = "Admin";
        }

        if (tags == null) {
            tags = new ArrayList<>();
        }

        if (published == null) {
            published = false;
        }

        if (views == null) {
            views = 0;
        }

        if (published &&
                publishedAt == null) {
            publishedAt = now;
        }
    }

    @PreUpdate
    protected void onUpdate() {

        updatedAt =
                OffsetDateTime.now();

        if (published &&
                publishedAt == null) {
            publishedAt =
                    OffsetDateTime.now();
        }
    }
}