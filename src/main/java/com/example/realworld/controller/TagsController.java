package com.example.realworld.controller;

import com.example.realworld.service.TagService;
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

    private final TagService tagService;

    @GetMapping
    public ResponseEntity<?> getTags() {
        return ResponseEntity.ok(
                new HashMap<String, Object>() {
                    {
                        put("tags", tagService.allTags());
                    }
                }
        );
    }
}
