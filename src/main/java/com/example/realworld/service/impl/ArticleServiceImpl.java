package com.example.realworld.service.impl;

import com.example.realworld.domain.dto.ArticleDto;
import com.example.realworld.domain.entity.ArticleEntity;
import com.example.realworld.domain.entity.TagEntity;
import com.example.realworld.domain.entity.UserEntity;
import com.example.realworld.domain.model.CreateArticleParam;
import com.example.realworld.mapper.ArticleMapper;
import com.example.realworld.mapper.TagMapper;
import com.example.realworld.service.ArticleService;
import com.example.realworld.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper;

    private final TagMapper tagsMapper;

    private final ProfileService profileService;

    @Override
    @Transactional
    public ArticleEntity createArticle(CreateArticleParam articleParam, UserEntity currentUser) {
        ArticleEntity articleEntity = ArticleEntity.builder()
                .userId(currentUser.getId())
                .body(articleParam.getBody())
                .tags(articleParam.getTagList())
                .title(articleParam.getTitle())
                .description(articleParam.getDescription())
                .build();

        articleParam.getTagList().stream()
                .map(TagEntity::new)
                .map(tagEntity ->
                        Optional.ofNullable(articleMapper.findTag(tagEntity.getName()))
                                .orElseGet(() -> {
                                    tagsMapper.insert(tagEntity);
                                    return tagEntity;
                                }))
                .forEach(tagEntity -> {
                    articleMapper.insertArticleTagRelation(articleEntity.getId(), tagEntity.getId());
                });

        articleMapper.insert(articleEntity);
        return articleEntity;
    }

    @Override
    public Optional<ArticleDto> findBySlug(String slug) {
        return Optional.ofNullable(articleMapper.findBySlug(slug));
    }

    public ArticleDto findById(String id, final UserEntity currentUser) {
        ArticleDto articleDto = articleMapper.findById(id);
        if (currentUser != null) {
            fillExtraInfo(id, currentUser, articleDto);
        }
        return articleDto;
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

    @Override
    public List<ArticleDto> queryArticles(Integer limit, Integer offset) {
        List<ArticleDto> articles = articleMapper.queryArticles(limit, offset);

        return articles;
    }

    @Override
    public void insertArticleUserRelation(String article_id, String user_id) {
        boolean following = articleMapper.isArticleFollowing(article_id, user_id);
        if (!following) {
            articleMapper.insertArticleUserRelation(article_id, user_id);
        }
    }

    // TODO fill extra article info
    private void fillExtraInfo(String id, UserEntity user, ArticleDto articleDto) {
        articleDto.setFavoritesCount(100);
        articleDto.setFavorited(true);
    }
}
