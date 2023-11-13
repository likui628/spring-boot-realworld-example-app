package com.example.realworld.service.impl;

import com.example.realworld.domain.dto.CommentDto;
import com.example.realworld.mapper.CommentMapper;
import com.example.realworld.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImp implements CommentService {

    private final CommentMapper commentMapper;

    @Override
    public List<CommentDto> queryComments(String article_id) {
        return commentMapper.queryComments(article_id);
    }
}
