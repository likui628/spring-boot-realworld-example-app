package com.example.realworld.validation;

import com.example.realworld.domain.entity.ArticleEntity;
import com.example.realworld.service.ArticleService;
import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueSlugValidation implements ConstraintValidator<UniqueSlug, String> {

    private final ArticleService articleService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (Strings.isNullOrEmpty(value)) {
            return true;
        }
        return articleService.findBySlug(ArticleEntity.toSlug(value)).isEmpty();
    }
}
