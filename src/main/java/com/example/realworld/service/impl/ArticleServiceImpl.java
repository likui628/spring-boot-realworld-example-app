package com.example.realworld.service.impl;

import com.example.realworld.config.AuthUserDetails;
import com.example.realworld.domain.dto.ArticleDto;
import com.example.realworld.domain.entity.ArticleEntity;
import com.example.realworld.domain.model.CreateArticleParam;
import com.example.realworld.mapper.ArticleMapper;
import com.example.realworld.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper;

    @Override
    public ArticleDto createArticle(CreateArticleParam articleParam, AuthUserDetails userDetails) {
        ArticleEntity articleEntity = ArticleEntity.builder()
                .userId(userDetails.getUserId())
                .body(articleParam.getBody())
                .tags(articleParam.getTagList())
                .title(articleParam.getTitle())
                .description(articleParam.getDescription())
                .build();
        articleMapper.insert(articleEntity);

        return convertEntityToDto(articleEntity);
    }

    private ArticleDto convertEntityToDto(ArticleEntity articleEntity) {
        return ArticleDto.builder()
                .body(articleEntity.getBody())
                .slug(articleEntity.getSlug())
                .tagList(articleEntity.getTags()
                        .stream().map(tagEntity -> tagEntity.getName()).collect(Collectors.toList()))
                .title(articleEntity.getTitle())
                .description(articleEntity.getDescription())
                .createdAt(articleEntity.getCreatedAt())
                .updatedAt(articleEntity.getUpdatedAt())
                .build();
    }
}
