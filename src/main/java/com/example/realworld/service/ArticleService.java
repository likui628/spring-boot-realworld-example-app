package com.example.realworld.service;

import com.example.realworld.domain.dto.ArticleDto;
import com.example.realworld.domain.dto.CommentDto;
import com.example.realworld.domain.entity.ArticleEntity;
import com.example.realworld.domain.entity.UserEntity;
import com.example.realworld.domain.model.CreateArticleParam;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    ArticleEntity createArticle(final CreateArticleParam article, final UserEntity currentUser);

    void deleteArticle(String id);

    Optional<ArticleDto> findBySlug(String slug);

    ArticleDto findById(String id, final UserEntity currentUser);

    List<ArticleDto> queryArticles(String author, String favoritedBy, String tag, Integer limit, Integer offset);

    void insertArticleUserRelation(String article_id, String user_id);

    void removeArticleUserRelation(String article_id, String user_id);

    Optional<CommentDto> findCommentById(String article_id, String comment_id);
}
