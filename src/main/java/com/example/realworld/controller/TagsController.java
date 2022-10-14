package com.example.realworld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagsController {

    @GetMapping
    public List<String> getAllTags(){
        return List.of("tag1","tag2","tag3");
    }
}
