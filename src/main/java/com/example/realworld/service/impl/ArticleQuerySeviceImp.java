package com.example.realworld.service.impl;

import com.example.realworld.domain.dto.ArticleDto;
import com.example.realworld.mapper.ArticleQueryMapper;
import com.example.realworld.service.ArticleQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleQuerySeviceImp implements ArticleQueryService {

    private final ArticleQueryMapper articleQueryMapper;

    @Override
    public Optional<ArticleDto> findBySlug(String slug) {
        return Optional.ofNullable(articleQueryMapper.findBySlug(slug));
    }

}
