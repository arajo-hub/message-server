package com.judy.message.message.request;

import com.judy.message.member.entity.Member;
import com.judy.message.message.entity.Message;
import com.judy.message.message.entity.ReadYn;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MessageSend {

    private String content;
    private String sender;
    private String recipient;

    @Builder
    public MessageSend(String content, String sender, String recipient) {
        this.content = content;
        this.sender = sender;
        this.recipient = recipient;
    }

}
