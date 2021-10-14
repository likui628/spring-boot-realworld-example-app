package com.example.realworld.service;

import java.util.Optional;

import com.example.realworld.model.User;
import com.example.realworld.service.mapper.UsersMapper;

import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private final UsersMapper usersMapper;

    public UsersService(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    public void save(User user) {
        usersMapper.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(usersMapper.findByUsername(username));
    }

    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(usersMapper.findByEmail(email));
    }

}
