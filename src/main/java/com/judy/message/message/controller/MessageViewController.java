package com.judy.message.message.controller;

import com.judy.message.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageViewController {

    private final MessageService messageService;

    @GetMapping("/list")
    private String list() {
        return "message/list";
    }

}
