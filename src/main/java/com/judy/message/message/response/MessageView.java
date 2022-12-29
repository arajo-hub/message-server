package com.judy.message.message.response;

import com.judy.message.member.response.MemberView;
import com.judy.message.message.entity.ReadYn;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Getter
public class MessageView {

    public static final int CONTENT_MAX_SIZE = 20;

    private String content;
    private MemberView sender;
    private String sendDatetime;
    private ReadYn readYn;

    @Builder
    public MessageView(String content, MemberView sender, LocalDateTime sendDatetime, ReadYn readYn) {
        this.content = (content.length() > CONTENT_MAX_SIZE) ? String.format("%s...", content.substring(0, CONTENT_MAX_SIZE)) : content;
        this.sender = sender;
        this.sendDatetime = sendDatetime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
        this.readYn = readYn;
    }

}
