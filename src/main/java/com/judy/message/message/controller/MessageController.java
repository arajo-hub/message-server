package com.judy.message.message.controller;

import com.judy.message.common.response.ListResponse;
import com.judy.message.common.response.SingleResponse;
import com.judy.message.member.response.MemberView;
import com.judy.message.message.request.MessageSend;
import com.judy.message.message.response.MessageView;
import com.judy.message.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.judy.message.common.code.CommonCode.SESSION_MEMBER_KEY;

@RestController
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/list/{nickname}")
    public ResponseEntity<ListResponse<MessageView>> list(HttpSession session, @PathVariable String nickname) throws IllegalAccessException {
        String sessionNickname = (String) session.getAttribute(SESSION_MEMBER_KEY);
        if (!sessionNickname.equals(nickname)) {
            throw new IllegalAccessException();
        }
        return messageService.findAllReceivedMessageByNickname(sessionNickname);
    }

    @PostMapping("/send")
    public ResponseEntity<SingleResponse> send(@RequestBody MessageSend messageSend) {
        return messageService.sendMessage(messageSend);
    }

}
