package com.example.realworld.api;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.realworld.service.TagsService;

@RestController
@RequestMapping(path = "tags")
public class TagsApi {
    private final TagsService tagsService;

    @Autowired
    public TagsApi(TagsService tagsService) {
        this.tagsService = tagsService;
    }

    @GetMapping
    public ResponseEntity<?> getTags() {
        return ResponseEntity.ok(new HashMap<String, Object>() {
            {
                put("tags", tagsService.allTags());
            }
        });
    }
}
