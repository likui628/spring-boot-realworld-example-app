package com.example.realworld.service;

import com.example.realworld.config.AuthUserDetails;
import com.example.realworld.domain.dto.ArticleDto;
import com.example.realworld.domain.model.CreateArticleParam;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    ArticleDto createArticle(final CreateArticleParam article, final AuthUserDetails userDetails);

    Optional<ArticleDto> findBySlug(String slug);

    List<ArticleDto> queryArticles(Integer limit, Integer offset);
}
