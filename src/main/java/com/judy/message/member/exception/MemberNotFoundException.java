package com.judy.message.member.exception;

import com.judy.message.common.exception.MessageServerException;

public class MemberNotFoundException extends MessageServerException {

    private static final String MESSAGE = "존재하지 않는 회원입니다.";

    public MemberNotFoundException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }

}
