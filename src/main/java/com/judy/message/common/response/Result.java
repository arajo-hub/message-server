package com.judy.message.common.response;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum Result {

    SUCCESS("200", "success"),
    NOT_FOUND("404", "not found"),
    TOO_MANY_REQUESTS("429", "too many request");

    private String resultCode;
    private String resultMessage;

    Result(String resultCode, String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

}
