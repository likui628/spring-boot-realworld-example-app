package com.example.realworld.mapper;

import com.example.realworld.domain.dto.ArticleDto;
import com.example.realworld.domain.entity.ArticleEntity;
import com.example.realworld.domain.entity.TagEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleMapper {

    void insert(@Param("article") ArticleEntity article);

    void deleteArticle(@Param("id") String id);

    TagEntity findTag(@Param("name") String name);

    void insertArticleTagRelation(@Param("articleId") String articleId, @Param("tagId") String tagId);

    ArticleDto findBySlug(@Param("slug") String slug);

    List<String> queryArticleIds(@Param("author") String author, @Param("favoritedBy") String favoritedBy, @Param("tag") String tag, @Param("limit") Integer limit, @Param("offset") Integer offset);

    List<ArticleDto> findArticles(List<String> articleIds);

    List<ArticleDto> findArticlesByAuthors(List<String> userIds, Integer limit, Integer offset);

    ArticleDto findById(@Param("id") String id);

    void insertArticleUserRelation(@Param("articleId") String articleId, @Param("userId") String userId);

    void removeArticleUserRelation(@Param("articleId") String articleId, @Param("userId") String userId);

    boolean isArticleFollowing(@Param("articleId") String articleId, @Param("userId") String userId);

    int articleFavoriteCount(@Param("articleId") String articleId);

}
