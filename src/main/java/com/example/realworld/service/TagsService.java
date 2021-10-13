package com.example.realworld.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.realworld.service.mapper.TagsMapper;

@Service
public class TagsService {
    private final TagsMapper tagsMapper;

    public TagsService(TagsMapper tagsMapper) {
        this.tagsMapper = tagsMapper;
    }

    public List<String> allTags() {
        return tagsMapper.all();
    }
}
