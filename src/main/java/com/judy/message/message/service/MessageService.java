package com.judy.message.message.service;

import com.judy.message.common.response.ListResponse;
import com.judy.message.common.response.SingleResponse;
import com.judy.message.message.request.MessageSend;
import com.judy.message.message.response.MessageView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MessageService {

    ResponseEntity<SingleResponse> sendMessage(MessageSend messageSend);

    ResponseEntity<List<MessageView>> findAllSentMessageByMemberSeq(Long seq);

    ResponseEntity<ListResponse<MessageView>> findAllReceivedMessageByMemberSeq(Long seq);

    ResponseEntity deleteMessage(Long seq);

    ResponseEntity<ListResponse<MessageView>> findAllReceivedMessageByNickname(String sessionNickname);

    ResponseEntity<Page<MessageView>> findPagesReceivedMessageByNickname(String nickname, Pageable pageable);

}
