package com.example.realworld.service.impl;

import com.example.realworld.domain.dto.CommentDto;
import com.example.realworld.domain.entity.ArticleEntity;
import com.example.realworld.domain.entity.TagEntity;
import com.example.realworld.domain.entity.UserEntity;
import com.example.realworld.domain.model.CreateArticleParam;
import com.example.realworld.domain.model.UpdateArticleParam;
import com.example.realworld.mapper.ArticleMapper;
import com.example.realworld.mapper.CommentMapper;
import com.example.realworld.mapper.TagMapper;
import com.example.realworld.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper;

    private final TagMapper tagsMapper;
    
    private final CommentMapper commentMapper;

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
    public void updateArticle(ArticleEntity article, UpdateArticleParam updateArticle) {
        article.update(updateArticle.getTitle(), updateArticle.getDescription(), updateArticle.getBody());
        articleMapper.update(article);
    }

    @Override
    public void deleteArticle(String id) {
        articleMapper.deleteArticle(id);
    }

    @Override
    public Optional<ArticleEntity> findBySlug(String slug) {
        return Optional.ofNullable(articleMapper.findBySlug(slug));
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
        return Optional.ofNullable(commentMapper.findArticleCommentById(article_id, comment_id));
    }
}
