package com.example.realworld.api.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidRequestException.class)
    protected ResponseEntity<?> handleInvalidRequestException(InvalidRequestException ire, WebRequest request) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        List<FieldErrorResource> errorResources = ire.getErrors().getFieldErrors().stream()
                .map(fieldError -> new FieldErrorResource(fieldError.getObjectName(), fieldError.getField(),
                        fieldError.getCode(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        ErrorResource errors = new ErrorResource(errorResources);

        return handleExceptionInternal(ire, errors, headers, HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

}
