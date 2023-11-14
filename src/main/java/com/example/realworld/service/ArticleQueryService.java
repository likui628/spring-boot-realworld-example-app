package com.example.realworld.service;

import com.example.realworld.domain.dto.ArticleDto;
import com.example.realworld.domain.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface ArticleQueryService {

    Optional<ArticleDto> findBySlug(String slug);

    ArticleDto findById(String id, final UserEntity currentUser);

    List<ArticleDto> queryArticles(String author, String favoritedBy, String tag, Integer limit, Integer offset);

    List<ArticleDto> queryArticlesFeed(String userId, Integer limit, Integer offset);
}
