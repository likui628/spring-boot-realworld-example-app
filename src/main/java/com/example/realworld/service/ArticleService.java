package com.example.realworld.service;

import com.example.realworld.domain.dto.ArticleDto;
import com.example.realworld.domain.entity.ArticleEntity;
import com.example.realworld.domain.entity.UserEntity;
import com.example.realworld.domain.model.CreateArticleParam;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    ArticleEntity createArticle(final CreateArticleParam article, final UserEntity currentUser);

    Optional<ArticleDto> findBySlug(String slug);

    ArticleDto findById(String id, final UserEntity currentUser);

    List<ArticleDto> queryArticles(Integer limit, Integer offset);

    void insertArticleUserRelation(String article_id, String user_id);

    void removeArticleUserRelation(String article_id, String user_id);
}
