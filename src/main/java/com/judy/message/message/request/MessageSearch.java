package com.judy.message.message.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MessageSearch {

    private int page;
    private int size;

    @Builder
    public MessageSearch(int page, int size) {
        this.page = page;
        this.size = size;
    }

}
