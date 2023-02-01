package com.judy.message.common.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
public class ValidationExceptionResponse extends BaseResponse {

    private Map<String, String> validation = new HashMap<>();

    @Builder
    public ValidationExceptionResponse(String code, String message, Map<String, String> validation) {
        super(code, message);
        this.validation = validation;
    }

    public void addValidation(String fieldName, String errorMessage) {
        this.validation.put(fieldName, errorMessage);
    }

}
