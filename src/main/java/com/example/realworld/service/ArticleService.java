package com.example.realworld.service;

import com.example.realworld.domain.dto.CommentDto;
import com.example.realworld.domain.entity.ArticleEntity;
import com.example.realworld.domain.entity.UserEntity;
import com.example.realworld.domain.model.CreateArticleParam;
import com.example.realworld.domain.model.UpdateArticleParam;

import java.util.Optional;

public interface ArticleService {

    ArticleEntity createArticle(final CreateArticleParam article, final UserEntity currentUser);

    void updateArticle(ArticleEntity article, final UpdateArticleParam updateArticle);

    void deleteArticle(String id);

    Optional<ArticleEntity> findBySlug(String slug);


    void insertArticleUserRelation(String article_id, String user_id);

    void removeArticleUserRelation(String article_id, String user_id);

    Optional<CommentDto> findCommentById(String article_id, String comment_id);
}
