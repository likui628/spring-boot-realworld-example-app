package com.example.realworld.controller;

import com.example.realworld.domain.dto.ArticleDto;
import com.example.realworld.domain.entity.ArticleEntity;
import com.example.realworld.domain.entity.CommentEntity;
import com.example.realworld.domain.entity.UserEntity;
import com.example.realworld.domain.model.CommentParam;
import com.example.realworld.domain.model.CreateArticleParam;
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
    public ResponseEntity favoriteArticle(@PathVariable("slug") String slug,
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
    public ResponseEntity unfavoriteArticle(@PathVariable("slug") String slug,
                                            @AuthenticationPrincipal UserEntity user) {
        ArticleDto articleDto = articleService.findBySlug(slug)
                .map(article -> {
                    articleService.removeArticleUserRelation(article.getId(), user.getId());
                    return articleService.findById(article.getId(), user);
                })
                .orElseThrow(ResourceNotFoundException::new);

        return ResponseEntity.ok(articleDto);
    }

    @PostMapping("/{slug}/comments")
    public ResponseEntity commentArticle(@PathVariable("slug") String slug,
                                         @Valid @RequestBody CommentParam commentParam,
                                         @AuthenticationPrincipal UserEntity user) {
        ArticleDto article = articleService
                .findBySlug(slug)
                .orElseThrow(ResourceNotFoundException::new);

        CommentEntity comment = CommentEntity
                .builder()
                .body(commentParam.getBody())
                .articleId(article.getId())
                .userId(user.getId())
                .build();
        articleMapper.insertArticleComment(comment);

        return ResponseEntity.ok(articleMapper.findArticleCommentById(article.getId(), comment.getId()));
    }

    @DeleteMapping("/{slug}/comments/{commentId}")
    public ResponseEntity deleteComment(@PathVariable("slug") String slug,
                                        @PathVariable("commentId") String commentId) {
        ArticleDto article = articleService
                .findBySlug(slug)
                .orElseThrow(ResourceNotFoundException::new);

        return articleService
                .findCommentById(article.getId(), commentId)
                .map(comment -> {
                    articleMapper.deleteArticleComment(comment.getId());
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(ResourceNotFoundException::new);
    }
}
