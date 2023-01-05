package com.judy.message.message.service.impl;

import com.judy.message.common.response.ListResponse;
import com.judy.message.common.response.Result;
import com.judy.message.common.response.SingleResponse;
import com.judy.message.member.entity.Member;
import com.judy.message.member.service.MemberService;
import com.judy.message.message.entity.Message;
import com.judy.message.message.repository.MessageRepository;
import com.judy.message.message.request.MessageSave;
import com.judy.message.message.request.MessageSend;
import com.judy.message.message.response.MessageView;
import com.judy.message.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MemberService memberService;

    private final MessageRepository messageRepository;

    @Override
    public ResponseEntity<SingleResponse> sendMessage(MessageSend messageSend) {
        Member sender = memberService.findMemberByNickname(messageSend.getSender());
        Member recipient = memberService.findMemberByNickname(messageSend.getRecipient());
        MessageSave messageSave = MessageSave.builder()
                                    .content(messageSend.getContent())
                                    .sender(sender)
                                    .recipient(recipient)
                                    .build();
        Message message = messageRepository.send(messageSave.toMessage());
        SingleResponse body = SingleResponse.builder().resultCode(Result.SUCCESS.getResultCode())
                .resultMessage(Result.SUCCESS.getResultMessage())
                .data(message.toMessageView())
                .build();
        return ResponseEntity.ok(body);
    }

    @Override
    public ResponseEntity<List<MessageView>> findAllSentMessageByMemberSeq(Long seq) {
        List<Message> messageList = messageRepository.findAllSentMessageByMemberSeq(seq);
        List<MessageView> messageViews = messageList.stream().map(m -> m.toMessageView()).collect(Collectors.toList());
        return ResponseEntity.ok(messageViews);
    }

    @Override
    public ResponseEntity<ListResponse<MessageView>> findAllReceivedMessageByMemberSeq(Long seq) {
        List<Message> messageList = messageRepository.findAllReceivedMessageByMemberSeq(seq);
        List<MessageView> messageViews = messageList.stream().map(m -> m.toMessageView()).collect(Collectors.toList());
        ListResponse<MessageView> body = ListResponse.<MessageView>builder()
                                                        .resultCode(Result.SUCCESS.getResultCode())
                                                        .resultMessage(Result.SUCCESS.getResultMessage())
                                                        .list(messageViews)
                                                        .build();
        return ResponseEntity.ok(body);
    }

    @Override
    public ResponseEntity deleteMessage(Long seq) {
        messageRepository.deleteMessage(seq);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ListResponse<MessageView>> findAllReceivedMessageByNickname(String sessionNickname) {
        Member sessionMember = memberService.findMemberByNickname(sessionNickname);
        return findAllReceivedMessageByMemberSeq(sessionMember.getSeq());
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<Page<MessageView>> findPagesReceivedMessageByNickname(String nickname, Pageable pageable) {
        Page<MessageView> messageViewPages = messageRepository.findByPage(nickname, pageable);
        ResponseEntity<Page<MessageView>> response = ResponseEntity.ok(messageViewPages);
        return response;
    }

    @Override
    public ResponseEntity<Page<MessageView>> getNewMessage(Long seq) {
        Page<MessageView> messageViewPages = messageRepository.findNewMessage(seq);
        ResponseEntity<Page<MessageView>> response = ResponseEntity.ok(messageViewPages);
        return response;
    }

}
