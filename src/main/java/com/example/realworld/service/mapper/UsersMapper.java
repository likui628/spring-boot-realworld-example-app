package com.example.realworld.service.mapper;

import com.example.realworld.model.User;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UsersMapper {
    void save(@Param("user") User user);

    User findByUsername(@Param("username") String username);

    User findByEmail(@Param("email") String email);
}
