package com.example.realworld.mapper;

import com.example.realworld.domain.entity.ArticleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleMapper {

    void insert(@Param("article") ArticleEntity article);
}
