package com.judy.message.message.controller;

import com.judy.message.common.response.ListResponse;
import com.judy.message.common.response.SingleResponse;
import com.judy.message.message.request.MessageSearch;
import com.judy.message.message.request.MessageSend;
import com.judy.message.message.response.MessageView;
import com.judy.message.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.judy.message.common.code.CommonCode.SESSION_MEMBER_KEY;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/list")
    public ResponseEntity<Page<MessageView>> list(HttpSession session, @RequestBody MessageSearch messageSearch) throws IllegalAccessException {
        long start = System.currentTimeMillis();
        Pageable pageable = PageRequest.of(messageSearch.getPage() - 1, messageSearch.getSize());
        String sessionNickname = (String) session.getAttribute(SESSION_MEMBER_KEY);
        ResponseEntity<Page<MessageView>> result = messageService.findPagesReceivedMessageByNickname(sessionNickname, pageable);
        log.info("{}ms 소요", System.currentTimeMillis() - start);
        return result;
    }

    @PostMapping("/send")
    public ResponseEntity<SingleResponse> send(@RequestBody MessageSend messageSend) {
        return messageService.sendMessage(messageSend);
    }

    @PostMapping("/getNewMessage")
    public ResponseEntity<Page<MessageView>> getNewMessage(HttpServletRequest req, Long seq) {
        return messageService.getNewMessage(seq);
    }

    @PostMapping("/{seq}")
    public ResponseEntity<SingleResponse> getSingleMessage(@PathVariable Long seq) {
        return messageService.getSingleMessage(seq);
    }

}
