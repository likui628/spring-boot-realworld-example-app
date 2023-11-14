package com.example.realworld.mapper;

import com.example.realworld.domain.dto.ArticleDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleQueryMapper {

    ArticleDto findBySlug(@Param("slug") String slug);

    List<ArticleDto> findArticles(List<String> articleIds);

    List<String> queryArticleIds(@Param("author") String author,
                                 @Param("favoritedBy") String favoritedBy,
                                 @Param("tag") String tag,
                                 @Param("limit") Integer limit,
                                 @Param("offset") Integer offset);

    List<ArticleDto> findArticlesByAuthors(List<String> userIds, Integer limit, Integer offset);

    ArticleDto findById(@Param("id") String id);
}
