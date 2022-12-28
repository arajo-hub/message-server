package com.judy.message.config.advice;

import com.judy.message.common.exception.MessageServerException;
import com.judy.message.common.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(MessageServerException.class)
    public ResponseEntity messageServerException(MessageServerException e) {
        int statusCode = e.getStatusCode();
        BaseResponse body = new BaseResponse(String.valueOf(statusCode), e.getMessage());
        return ResponseEntity.status(statusCode).body(body);
    }

}
