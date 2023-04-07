package com.example.realworld.mapper;

import com.example.realworld.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    void insert(@Param("user") UserDto user);
}
