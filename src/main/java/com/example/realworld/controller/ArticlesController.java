package com.example.realworld.controller;

import com.example.realworld.domain.dto.ArticleDto;
import com.example.realworld.domain.entity.ArticleEntity;
import com.example.realworld.domain.entity.UserEntity;
import com.example.realworld.domain.model.CreateArticleParam;
import com.example.realworld.exception.NoAuthorizationException;
import com.example.realworld.exception.ResourceNotFoundException;
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
    public ResponseEntity<?> createArticle(@Valid @RequestBody CreateArticleParam createArticleParam,
                                           @AuthenticationPrincipal UserEntity currentUser) {
        ArticleEntity article = articleService.createArticle(createArticleParam, currentUser);

        return ResponseEntity.ok(articleService.findById(article.getId(), currentUser));
    }

    @GetMapping
    public ResponseEntity<?> getArticles(
            @RequestParam(value = "author", required = false) String author,
            @RequestParam(value = "tag", required = false) String tag,
            @RequestParam(value = "favorited", required = false) String favoritedBy,
            @RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
            @RequestParam(value = "limit", defaultValue = "20", required = false) int limit) {
        List<ArticleDto> articles = articleService
                .queryArticles(author, favoritedBy, tag, limit, offset);

        return ResponseEntity.ok(
                new HashMap<String, Object>() {
                    {
                        put("articles", articles);
                        put("articlesCount", articles.size());
                    }
                }
        );
    }

    @GetMapping("/{slug}")
    public ResponseEntity<?> getSingleArticleBySlug(@PathVariable("slug") String slug) {
        return articleService
                .findBySlug(slug)
                .map(ResponseEntity::ok)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @DeleteMapping("/{slug}")
    public ResponseEntity<?> deleteArticle(@PathVariable("slug") String slug, @AuthenticationPrincipal UserEntity user) {
        return articleService
                .findBySlug(slug)
                .map(article -> {
                    if (!user.getId().equals(article.getProfileDto().getId())) {
                        throw new NoAuthorizationException();
                    }
                    articleService.deleteArticle(article.getId());
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping("/{slug}/favorite")
    public ResponseEntity<?> favoriteArticle(@PathVariable("slug") String slug,
                                             @AuthenticationPrincipal UserEntity user) {
        ArticleDto articleDto = articleService.findBySlug(slug)
                .map(article -> {
                    articleService.insertArticleUserRelation(article.getId(), user.getId());
                    return articleService.findById(article.getId(), user);
                })
                .orElseThrow(ResourceNotFoundException::new);

        return ResponseEntity.ok(articleDto);
    }

    @DeleteMapping("/{slug}/favorite")
    public ResponseEntity<?> unfavoriteArticle(@PathVariable("slug") String slug,
                                               @AuthenticationPrincipal UserEntity user) {
        ArticleDto articleDto = articleService.findBySlug(slug)
                .map(article -> {
                    articleService.removeArticleUserRelation(article.getId(), user.getId());
                    return articleService.findById(article.getId(), user);
                })
                .orElseThrow(ResourceNotFoundException::new);

        return ResponseEntity.ok(articleDto);
    }

}
