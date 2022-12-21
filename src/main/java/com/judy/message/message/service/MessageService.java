package com.judy.message.message.service;

import com.judy.message.message.request.MessageSend;
import com.judy.message.message.response.MessageView;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MessageService {

    ResponseEntity<MessageView> sendMessage(MessageSend messageSend);

    ResponseEntity<List<MessageView>> findAllSentMessageByMemberSeq(Long seq);

    ResponseEntity<List<MessageView>> findAllReceivedMessageByMemberSeq(Long seq);

    ResponseEntity deleteMessage(Long seq);
}
