package com.example.realworld.validation;

import com.example.realworld.domain.model.RegisterParam;
import com.example.realworld.service.UserService;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class DuplicatedUserValidation implements ConstraintValidator<DuplicatedUser, RegisterParam> {

    private final UserService userService;

    @Override
    public boolean isValid(RegisterParam value, ConstraintValidatorContext context) {
        return false;
//        return userService.findByEmail(value.getEmail()).isEmpty() && userService.findByUsername(value.getUsername()).isEmpty();
    }
}
