package com.example.realworld.mapper;

import com.example.realworld.domain.entity.TagEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TagMapper {

    List<String> all();

    void insert(@Param("tag") TagEntity tag);

}
