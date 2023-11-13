package com.example.realworld.controller;

import com.example.realworld.domain.dto.ArticleDto;
import com.example.realworld.domain.dto.CommentDto;
import com.example.realworld.domain.entity.CommentEntity;
import com.example.realworld.domain.entity.UserEntity;
import com.example.realworld.domain.model.CommentParam;
import com.example.realworld.exception.ResourceNotFoundException;
import com.example.realworld.mapper.ArticleMapper;
import com.example.realworld.service.ArticleService;
import com.example.realworld.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/articles/{slug}/comments")
@RequiredArgsConstructor
public class CommentsController {

    private final ArticleService articleService;

    private final ArticleMapper articleMapper;

    private final CommentService commentService;

    @PostMapping("")
    public ResponseEntity<?> commentArticle(@PathVariable("slug") String slug,
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

    @GetMapping("")
    public ResponseEntity<?> getArticleComments(@PathVariable("slug") String slug) {
        ArticleDto articleDto = articleService
                .findBySlug(slug)
                .orElseThrow(ResourceNotFoundException::new);

        List<CommentDto> comments = commentService.queryComments(articleDto.getId());
        return ResponseEntity.ok(
                new HashMap<String, Object>() {
                    {
                        put("comments", comments);
                    }
                });
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("slug") String slug,
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
