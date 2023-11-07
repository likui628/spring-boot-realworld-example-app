package com.example.realworld.domain.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class CommentEntity {

    private String id;

    private String userId;

    private String articleId;

    private String body;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Builder
    public CommentEntity(String body, String userId, String articleId) {
        this(body, userId, articleId, LocalDateTime.now());
    }

    public CommentEntity(
            String body,
            String userId,
            String articleId,
            LocalDateTime createdAt) {
        this.id = UUID.randomUUID().toString();
        this.body = body;
        this.userId = userId;
        this.articleId = articleId;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
    }
}
