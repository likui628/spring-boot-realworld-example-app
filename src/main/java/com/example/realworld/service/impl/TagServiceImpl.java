package com.example.realworld.service.impl;

import com.example.realworld.mapper.TagMapper;
import com.example.realworld.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagMapper tagMapper;

    @Override
    public List<String> allTags() {
        return tagMapper.all();
    }
}
