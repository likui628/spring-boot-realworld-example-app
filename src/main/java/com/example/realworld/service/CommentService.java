package com.example.realworld.service;

import com.example.realworld.domain.dto.CommentDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> queryComments(String article_id);
}
