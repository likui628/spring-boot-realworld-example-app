package com.example.realworld.service;

import com.example.realworld.config.AuthUserDetails;
import com.example.realworld.domain.dto.ArticleDto;
import com.example.realworld.domain.model.CreateArticleParam;

public interface ArticleService {

    ArticleDto createArticle(final CreateArticleParam article, final AuthUserDetails userDetails);
}
