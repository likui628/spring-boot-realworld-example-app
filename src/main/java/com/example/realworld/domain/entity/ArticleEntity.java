package com.example.realworld.domain.entity;

import com.google.common.base.Strings;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class ArticleEntity {

    private String id;

    private String userId;

    private String slug;

    private String title;

    private String description;

    private String body;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<TagEntity> tags;

    @Builder
    public ArticleEntity(String title,
                         String description,
                         String body, List<String> tags, String userId) {
        this(title, description, body, tags, userId, LocalDateTime.now());
    }

    public ArticleEntity(
            String title,
            String description,
            String body,
            List<String> tags,
            String userId,
            LocalDateTime createdAt) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.slug = toSlug(title);
        this.title = title;
        this.description = description;
        this.body = body;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
        this.tags = new HashSet<>(tags).stream().map(TagEntity::new).collect(Collectors.toList());
    }

    public void update(String title, String description, String body) {
        boolean isUpdated = false;
        if (!Strings.isNullOrEmpty(title)) {
            this.title = title;
            this.slug = toSlug(title);
            isUpdated = true;
        }
        if (!Strings.isNullOrEmpty(description)) {
            this.description = description;
            isUpdated = true;
        }
        if (!Strings.isNullOrEmpty(body)) {
            this.body = body;
            isUpdated = true;
        }
        if (isUpdated) {
            this.updatedAt = LocalDateTime.now();
        }
    }

    public static String toSlug(String title) {
        return title.toLowerCase().replaceAll("[\\&|[\\uFE30-\\uFFA0]|\\’|\\”|\\s\\?\\,\\.]+", "-");
    }
}
