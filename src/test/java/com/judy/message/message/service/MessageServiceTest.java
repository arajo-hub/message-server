package com.judy.message.message.service;

import com.judy.message.common.response.ListResponse;
import com.judy.message.member.entity.Member;
import com.judy.message.member.repository.MemberRepository;
import com.judy.message.message.entity.Message;
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
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    private List<Member> testMembers = new ArrayList<Member>();

    @BeforeEach
    void setUp() {
        Member hong = Member.builder()
                            .nickname("홍길동")
                            .password("test1234")
                            .build();
        Member lee = Member.builder()
                            .nickname("이순신")
                            .password("test1234")
                            .build();
        memberRepository.join(hong);
        memberRepository.join(lee);
        testMembers.add(hong);
        testMembers.add(lee);
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

    @Test
    @DisplayName("해당 사용자의 받은 메시지 조회")
    void findAllReceivedMessageByMemberSeq() {
        List<Message> messageSendList = new ArrayList<Message>();
        messageSendList.add(Message.builder()
                .content("테스트 메시지입니다.")
                .sender(testMembers.get(0))
                .recipient(testMembers.get(1))
                .build());
        messageSendList.add(Message.builder()
                .content("테스트 메시지입니다.")
                .sender(testMembers.get(0))
                .recipient(testMembers.get(1))
                .build());
        messageSendList.add(Message.builder()
                .content("테스트 메시지입니다.")
                .sender(testMembers.get(0))
                .recipient(testMembers.get(1))
                .build());
        messageSendList.add(Message.builder()
                .content("테스트 메시지입니다.")
                .sender(testMembers.get(0))
                .recipient(testMembers.get(1))
                .build());
        messageSendList.add(Message.builder()
                .content("테스트 메시지입니다.")
                .sender(testMembers.get(0))
                .recipient(testMembers.get(1))
                .build());

        messageSendList.forEach(m -> messageRepository.send(m));

        ResponseEntity<ListResponse<MessageView>> response = messageService.findAllReceivedMessageByMemberSeq(testMembers.get(1).getSeq());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(messageSendList.size(), response.getBody().getList().size());
    }

    @Test
    @DisplayName("해당 사용자의 보낸 메시지 조회")
    void findAllSentMessageByMemberSeq() {
        List<Message> messageSendList = new ArrayList<Message>();
        messageSendList.add(Message.builder()
                .content("테스트 메시지입니다.")
                .sender(testMembers.get(0))
                .recipient(testMembers.get(1))
                .build());
        messageSendList.add(Message.builder()
                .content("테스트 메시지입니다.")
                .sender(testMembers.get(0))
                .recipient(testMembers.get(1))
                .build());
        messageSendList.add(Message.builder()
                .content("테스트 메시지입니다.")
                .sender(testMembers.get(0))
                .recipient(testMembers.get(1))
                .build());
        messageSendList.add(Message.builder()
                .content("테스트 메시지입니다.")
                .sender(testMembers.get(0))
                .recipient(testMembers.get(1))
                .build());
        messageSendList.add(Message.builder()
                .content("테스트 메시지입니다.")
                .sender(testMembers.get(0))
                .recipient(testMembers.get(1))
                .build());

        messageSendList.forEach(m -> messageRepository.send(m));

        ResponseEntity<List<MessageView>> response = messageService.findAllSentMessageByMemberSeq(testMembers.get(0).getSeq());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(messageSendList.size(), response.getBody().size());
    }
    
    @Test
    @DisplayName("메시지 삭제")
    void deleteMessage() {
        Message message = Message.builder()
                .content("테스트 메시지입니다.")
                .sender(testMembers.get(0))
                .recipient(testMembers.get(1))
                .build();
        messageRepository.send(message);
        messageService.deleteMessage(message.getSeq());
        assertEquals(0, messageRepository.findAllCount());
    }

}