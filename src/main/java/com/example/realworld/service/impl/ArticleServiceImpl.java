package com.example.realworld.service.impl;

import com.example.realworld.domain.dto.ArticleDto;
import com.example.realworld.domain.dto.CommentDto;
import com.example.realworld.domain.entity.ArticleEntity;
import com.example.realworld.domain.entity.TagEntity;
import com.example.realworld.domain.entity.UserEntity;
import com.example.realworld.domain.model.CreateArticleParam;
import com.example.realworld.mapper.ArticleMapper;
import com.example.realworld.mapper.TagMapper;
import com.example.realworld.mapper.UserMapper;
import com.example.realworld.service.ArticleService;
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

    private final UserMapper userMapper;

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
    public void deleteArticle(String id) {
        articleMapper.deleteArticle(id);
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
    public List<ArticleDto> queryArticles(String username, String tag, Integer limit, Integer offset) {
        List<ArticleDto> articles = articleMapper.queryArticles(username, tag, limit, offset);

        return articles;
    }

    @Override
    public void insertArticleUserRelation(String article_id, String user_id) {
        boolean following = articleMapper.isArticleFollowing(article_id, user_id);
        if (!following) {
            articleMapper.insertArticleUserRelation(article_id, user_id);
        }
    }

    @Override
    public void removeArticleUserRelation(String article_id, String user_id) {
        boolean following = articleMapper.isArticleFollowing(article_id, user_id);
        if (following) {
            articleMapper.removeArticleUserRelation(article_id, user_id);
        }
    }

    @Override
    public Optional<CommentDto> findCommentById(String article_id, String comment_id) {
        return Optional.ofNullable(articleMapper.findArticleCommentById(article_id, comment_id));
    }

    private void fillExtraInfo(String id, UserEntity user, ArticleDto articleDto) {
        articleDto.setFavoritesCount(articleMapper.articleFavoriteCount(articleDto.getId()));
        articleDto.setFavorited(articleMapper.isArticleFollowing(articleDto.getId(), user.getId()));
        articleDto.getProfileDto()
                .setFollowing(
                        userMapper.isUserFollowing(user.getId(), articleDto.getProfileDto().getId())
                );
    }
}
