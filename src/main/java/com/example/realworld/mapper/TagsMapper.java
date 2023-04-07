package com.example.realworld.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagsMapper {

    List<String> all();
}
