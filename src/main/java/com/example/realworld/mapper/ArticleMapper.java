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

    TagEntity findTag(@Param("name") String name);

    void insertArticleTagRelation(@Param("articleId") String articleId, @Param("tagId") String tagId);

    ArticleDto findBySlug(@Param("slug") String slug);

    List<ArticleDto> queryArticles(@Param("limit") Integer limit, @Param("offset") Integer offset);

    ArticleDto findById(@Param("id") String id);
}
