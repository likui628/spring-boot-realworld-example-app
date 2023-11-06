package com.example.realworld.controller;

import com.example.realworld.domain.dto.ArticleDto;
import com.example.realworld.domain.entity.ArticleEntity;
import com.example.realworld.domain.entity.UserEntity;
import com.example.realworld.domain.model.CreateArticleParam;
import com.example.realworld.mapper.ArticleMapper;
import com.example.realworld.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "/articles")
@RequiredArgsConstructor
public class ArticlesController {

    private final ArticleService articleService;

    private final ArticleMapper articleMapper;

    @PostMapping
    public ResponseEntity createArticle(@Valid @RequestBody CreateArticleParam createArticleParam,
                                        @AuthenticationPrincipal UserEntity currentUser) {
        ArticleEntity article = articleService.createArticle(createArticleParam, currentUser);

        return ResponseEntity.ok(articleService.findById(article.getId(), currentUser));
    }

    @GetMapping
    public ResponseEntity getArticles(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                      @RequestParam(value = "limit", defaultValue = "20") int limit) {
        List<ArticleDto> articles = articleService.queryArticles(limit, offset);

        return ResponseEntity.ok(
                new HashMap<String, Object>() {
                    {
                        put("articles", articles);
                        put("articlesCount", articles.size());
                    }
                }
        );
    }

    @PostMapping("/{slug}/favorite")
    public ResponseEntity postFavoriteArticle(@PathVariable String slug,
                                              @AuthenticationPrincipal UserEntity user) {
        ArticleDto articleDto = articleService.findBySlug(slug)
                .map(article -> {
                    articleService.insertArticleUserRelation(article.getId(), user.getId());

                    return articleService.findById(article.getId(), user);
                })
                .orElseThrow(() -> new IllegalStateException("Exception"));

        return ResponseEntity.ok(articleDto);
    }
}
