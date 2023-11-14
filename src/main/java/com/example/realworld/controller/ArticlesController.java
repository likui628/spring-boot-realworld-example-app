package com.example.realworld.controller;

import com.example.realworld.domain.dto.ArticleDto;
import com.example.realworld.domain.entity.ArticleEntity;
import com.example.realworld.domain.entity.UserEntity;
import com.example.realworld.domain.model.CreateArticleParam;
import com.example.realworld.domain.model.UpdateArticleParam;
import com.example.realworld.exception.NoAuthorizationException;
import com.example.realworld.exception.ResourceNotFoundException;
import com.example.realworld.service.ArticleQueryService;
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

    private final ArticleQueryService articleQueryService;

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

    @GetMapping("/feed")
    public ResponseEntity<?> getArticlesFeed(@RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
                                             @RequestParam(value = "limit", defaultValue = "20", required = false) int limit,
                                             @AuthenticationPrincipal UserEntity user) {
        List<ArticleDto> articles = articleService.queryArticlesFeed(user.getId(), offset, limit);
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

    @PutMapping("/{slug}")
    public ResponseEntity<?> updateArticle(@PathVariable("slug") String slug,
                                           @Valid @RequestBody UpdateArticleParam updateArticleParam,
                                           @AuthenticationPrincipal UserEntity user) {
        /**
         * todo
         * 根据slug判断article是否存在
         * 判断是否有权限修改
         * 仅修改不为空的部分
         * 返回最新结果
         */
        // todo articleService里创建发挥ArticleEntity的函数
        // todo Article里创建一个update的函数
        // todo mybatis里set需要判断是否为空
        ArticleDto articleDto = articleService.findBySlug(slug)
                .map(article -> {
                    articleService.updateArticle(article, updateArticleParam);
                    return articleService.findById(article.getId(), user);
                })
                .orElseThrow(ResourceNotFoundException::new);

        return ResponseEntity.ok(articleDto);
    }

    @DeleteMapping("/{slug}")
    public ResponseEntity<?> deleteArticle(@PathVariable("slug") String slug, @AuthenticationPrincipal UserEntity user) {
        return articleQueryService
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
