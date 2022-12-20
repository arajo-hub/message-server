package com.judy.message.message.entity;

public enum ReadYn {

    YES(1, "readed"), NO(2, "not readed");

    private int code;
    private String message;

    ReadYn(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
