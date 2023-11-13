package com.example.realworld.mapper;

import com.example.realworld.domain.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {
    List<CommentDto> queryComments(@Param("articleId") String articleId);
}
