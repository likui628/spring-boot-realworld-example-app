package com.example.realworld.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueSlugValidation.class)
public @interface UniqueSlug {

    String message() default "duplicated slug";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
