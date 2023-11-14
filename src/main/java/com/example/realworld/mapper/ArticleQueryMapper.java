package com.example.realworld.mapper;

import com.example.realworld.domain.dto.ArticleDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleQueryMapper {

    ArticleDto findBySlug(@Param("slug") String slug);
}
