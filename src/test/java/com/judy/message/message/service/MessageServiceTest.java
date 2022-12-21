package com.judy.message.message.service;

import com.judy.message.member.entity.Member;
import com.judy.message.member.repository.MemberRepository;
import com.judy.message.message.repository.MessageRepository;
import com.judy.message.message.request.MessageSend;
import com.judy.message.message.response.MessageView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MessageServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageRepository messageRepository;

    @BeforeEach
    void setUp() {
        memberRepository.join(Member.builder()
                                    .nickname("홍길동")
                                    .password("test1234")
                                    .build());
        memberRepository.join(Member.builder()
                                    .nickname("이순신")
                                    .password("test1234")
                                    .build());

    }

    @Test
    @DisplayName("메시지 전송")
    void sendMessage() {
        MessageSend messageSend = MessageSend.builder()
                .content("테스트 메시지입니다.")
                .sender("홍길동")
                .recipient("이순신")
                .build();
        ResponseEntity<MessageView> response = messageService.sendMessage(messageSend);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(messageSend.getContent(), response.getBody().getContent());
    }

}