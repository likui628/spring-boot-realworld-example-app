package com.example.realworld.service;

import com.example.realworld.domain.dto.ArticleDto;

import java.util.Optional;

public interface ArticleQueryService {

    Optional<ArticleDto> findBySlug(String slug);
    
}
