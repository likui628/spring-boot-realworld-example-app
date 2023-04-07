package com.example.realworld.controller;

import com.example.realworld.service.TagsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(path = "tags")
@RequiredArgsConstructor
public class TagsController {

    private final TagsService tagsService;

    @GetMapping
    public ResponseEntity getTags() {
        return ResponseEntity.ok(
                new HashMap<String, Object>() {
                    {
                        put("tags", tagsService.allTags());
                    }
                }
        );
    }
}
