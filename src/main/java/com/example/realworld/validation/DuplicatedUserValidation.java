package com.example.realworld.validation;

import com.example.realworld.domain.model.RegisterParam;
import com.example.realworld.mapper.UserMapper;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class DuplicatedUserValidation implements ConstraintValidator<DuplicatedUser, RegisterParam> {

    private final UserMapper userMapper;

    @Override
    public boolean isValid(RegisterParam value, ConstraintValidatorContext context) {
        return userMapper.findByEmail(value.getEmail()).isEmpty() && userMapper.findByUsername(value.getUsername()).isEmpty();
    }
}
