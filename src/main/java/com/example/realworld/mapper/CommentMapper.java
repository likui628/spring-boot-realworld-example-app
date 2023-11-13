package com.example.realworld.mapper;

import com.example.realworld.domain.dto.CommentDto;
import com.example.realworld.domain.entity.CommentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    void insertArticleComment(@Param("comment") CommentEntity comment);

    List<CommentDto> queryComments(@Param("articleId") String articleId);

    CommentDto findArticleCommentById(@Param("articleId") String articleId, @Param("commentId") String commentId);

    void deleteArticleComment(@Param("commentId") String commentId);
}
