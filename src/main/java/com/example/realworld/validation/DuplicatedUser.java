package com.example.realworld.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DuplicatedUserValidation.class)
public @interface DuplicatedUser {

    String message() default "duplicated username or email";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
