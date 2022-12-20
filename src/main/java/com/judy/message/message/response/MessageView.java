package com.judy.message.message.response;

import com.judy.message.member.response.MemberView;
import com.judy.message.message.entity.ReadYn;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MessageView {

    private String content;
    private MemberView sender;
    private LocalDateTime sendDatetime;
    private ReadYn readYn;

    @Builder
    public MessageView(String content, MemberView sender, LocalDateTime sendDatetime, ReadYn readYn) {
        this.content = content;
        this.sender = sender;
        this.sendDatetime = sendDatetime;
        this.readYn = readYn;
    }

}
