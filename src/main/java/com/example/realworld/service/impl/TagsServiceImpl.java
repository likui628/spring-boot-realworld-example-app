package com.example.realworld.service.impl;

import com.example.realworld.mapper.TagsMapper;
import com.example.realworld.service.TagsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagsServiceImpl implements TagsService {

    private final TagsMapper tagsMapper;

    @Override
    public List<String> allTags() {
        return tagsMapper.all();
    }
}
