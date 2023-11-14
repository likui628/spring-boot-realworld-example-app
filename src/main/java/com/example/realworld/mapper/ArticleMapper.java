package com.example.realworld.mapper;

import com.example.realworld.domain.entity.ArticleEntity;
import com.example.realworld.domain.entity.TagEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleMapper {

    void insert(@Param("article") ArticleEntity article);

    void update(@Param("article") ArticleEntity article);

    void deleteArticle(@Param("id") String id);

    TagEntity findTag(@Param("name") String name);

    void insertArticleTagRelation(@Param("articleId") String articleId, @Param("tagId") String tagId);

    ArticleEntity findBySlug(@Param("slug") String slug);

    void insertArticleUserRelation(@Param("articleId") String articleId, @Param("userId") String userId);

    void removeArticleUserRelation(@Param("articleId") String articleId, @Param("userId") String userId);

    boolean isArticleFollowing(@Param("articleId") String articleId, @Param("userId") String userId);

    int articleFavoriteCount(@Param("articleId") String articleId);

}
