package com.judy.message.message.service.impl;

import com.judy.message.member.entity.Member;
import com.judy.message.member.response.MemberView;
import com.judy.message.member.service.MemberService;
import com.judy.message.message.entity.Message;
import com.judy.message.message.repository.MessageRepository;
import com.judy.message.message.request.MessageSave;
import com.judy.message.message.request.MessageSend;
import com.judy.message.message.response.MessageView;
import com.judy.message.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MemberService memberService;

    private final MessageRepository messageRepository;

    @Override
    public ResponseEntity<MessageView> sendMessage(MessageSend messageSend) {
        Member sender = memberService.findMemberByNickname(messageSend.getSender());
        Member recipient = memberService.findMemberByNickname(messageSend.getRecipient());
        MessageSave messageSave = MessageSave.builder()
                                    .content(messageSend.getContent())
                                    .sender(sender)
                                    .recipient(recipient)
                                    .build();
        Message message = messageRepository.send(messageSave.toMessage());
        return ResponseEntity.ok(message.toMessageView());
    }

}
