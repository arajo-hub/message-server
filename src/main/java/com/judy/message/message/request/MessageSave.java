package com.judy.message.message.request;

import com.judy.message.member.entity.Member;
import com.judy.message.message.entity.Message;
import com.judy.message.message.entity.ReadYn;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MessageSave {

    private String content;
    private Member sender;
    private Member recipient;

    @Builder
    public MessageSave(String content, Member sender, Member recipient) {
        this.content = content;
        this.sender = sender;
        this.recipient = recipient;
    }

    public Message toMessage() {
        return Message.builder()
                .content(content)
                .sender(sender)
                .recipient(recipient)
                .sendDatetime(LocalDateTime.now())
                .readYn(ReadYn.NO)
                .build();
    }

}
