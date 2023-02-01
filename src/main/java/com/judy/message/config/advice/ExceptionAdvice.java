package com.judy.message.config.advice;

import com.judy.message.common.exception.MessageServerException;
import com.judy.message.common.response.BaseResponse;
import com.judy.message.common.response.ValidationExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(MessageServerException.class)
    public ResponseEntity messageServerException(MessageServerException e) {
        int statusCode = e.getStatusCode();
        BaseResponse body = new BaseResponse(String.valueOf(statusCode), e.getMessage());
        return ResponseEntity.status(statusCode).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity hasInvalidParam(MethodArgumentNotValidException e) {
        ValidationExceptionResponse response = ValidationExceptionResponse.builder()
                .code("400")
                .message("잘못된 요청입니다.")
                .validation(new HashMap<>())
                .build();
        for (FieldError fieldError : e.getFieldErrors()) {
            response.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
