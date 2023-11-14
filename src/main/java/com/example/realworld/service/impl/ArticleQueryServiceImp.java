package com.example.realworld.service.impl;

import com.example.realworld.domain.dto.ArticleDto;
import com.example.realworld.domain.entity.UserEntity;
import com.example.realworld.mapper.ArticleMapper;
import com.example.realworld.mapper.ArticleQueryMapper;
import com.example.realworld.mapper.UserMapper;
import com.example.realworld.service.ArticleQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleQueryServiceImp implements ArticleQueryService {

    private final ArticleQueryMapper articleQueryMapper;

    private final ArticleMapper articleMapper;

    private final UserMapper userMapper;

    @Override
    public Optional<ArticleDto> findBySlug(String slug) {
        return Optional.ofNullable(articleQueryMapper.findBySlug(slug));
    }

    @Override
    public ArticleDto findById(String id, final UserEntity currentUser) {
        ArticleDto articleDto = articleQueryMapper.findById(id);
        if (currentUser != null) {
            fillExtraInfo(id, currentUser, articleDto);
        }
        return articleDto;
    }

    @Override
    public List<ArticleDto> queryArticles(String author, String favoritedBy, String tag, Integer limit, Integer offset) {
        List<String> articleIds = articleQueryMapper.queryArticleIds(author, favoritedBy, tag, limit, offset);
        if (articleIds.isEmpty()) {
            return new ArrayList<>();
        }
        return articleQueryMapper.findArticles(articleIds);
    }

    @Override
    public List<ArticleDto> queryArticlesFeed(String userId, Integer limit, Integer offset) {
        List<String> follows = userMapper.findFollows(userId);
        if (follows.isEmpty()) {
            return new ArrayList<>();
        }
        return articleQueryMapper.findArticlesByAuthors(follows, limit, offset);
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
