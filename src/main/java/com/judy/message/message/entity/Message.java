package com.judy.message.message.entity;

import com.judy.message.member.entity.Member;
import com.judy.message.message.response.MessageView;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    @Column
    private String content;
    @ManyToOne
    private Member sender;
    @ManyToOne
    private Member recipient;
    @Column
    private LocalDateTime sendDatetime;
    @Column
    private ReadYn readYn;

    @Builder
    public Message(Long seq, String content, Member sender, Member recipient, LocalDateTime sendDatetime, ReadYn readYn) {
        this.seq = seq;
        this.content = content;
        this.sender = sender;
        this.recipient = recipient;
        this.sendDatetime = sendDatetime;
        this.readYn = readYn;
    }

    public MessageView toMessageView() {
        return MessageView.builder()
                .content(content)
                .sender(sender.toMemberView())
                .sendDatetime(sendDatetime)
                .readYn(readYn)
                .build();
    }

}
