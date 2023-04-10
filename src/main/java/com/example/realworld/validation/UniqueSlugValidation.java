package com.example.realworld.validation;

import com.example.realworld.domain.entity.ArticleEntity;
import com.example.realworld.service.ArticleService;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueSlugValidation implements ConstraintValidator<UniqueSlug, String> {

    private final ArticleService articleService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return articleService.findBySlug(ArticleEntity.toSlug(value)).isEmpty();
    }
}
