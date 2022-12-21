package com.judy.message.message.service;

import com.judy.message.message.request.MessageSend;
import com.judy.message.message.response.MessageView;
import org.springframework.http.ResponseEntity;

public interface MessageService {

    ResponseEntity<MessageView> sendMessage(MessageSend messageSend);

}
