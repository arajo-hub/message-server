package com.judy.message.common.exception;

import lombok.Getter;

@Getter
public abstract class MessageServerException extends RuntimeException {

    public MessageServerException(String message) {
        super(message);
    }

    public MessageServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();

}
