package com.example.realworld.controller;

import com.example.realworld.config.AuthUserDetails;
import com.example.realworld.domain.dto.ArticleDto;
import com.example.realworld.domain.model.CreateArticleParam;
import com.example.realworld.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/articles")
@RequiredArgsConstructor
public class ArticlesController {

    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity createArticle(@Valid @RequestBody CreateArticleParam createArticleParam,
                                        @AuthenticationPrincipal AuthUserDetails userDetails) {
        ArticleDto articleDto = articleService.createArticle(createArticleParam,userDetails);

        return ResponseEntity.status(200).body(articleDto);
    }
}
