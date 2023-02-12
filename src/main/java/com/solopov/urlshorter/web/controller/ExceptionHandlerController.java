package com.solopov.urlshorter.web.controller;

import com.solopov.urlshorter.exception.UrlNotFoundException;
import com.solopov.urlshorter.web.dto.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        StringBuilder message = new StringBuilder();
        e.getBindingResult().getAllErrors().forEach((error) -> {

            String fieldName = ((FieldError) error).getField();
            String messageValue = error.getDefaultMessage();
            message.append(String.format("%s: %s. ", fieldName, messageValue));
        });

        return new ResponseEntity<>(new ExceptionDto(message.toString(), status), status);
    }

    @ExceptionHandler(UrlNotFoundException.class)
    protected ResponseEntity<?> linkNotFoundException() {
        HttpStatus status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(new ExceptionDto("Link does not found", status), status);
    }

}
