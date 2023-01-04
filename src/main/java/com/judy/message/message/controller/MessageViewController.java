package com.judy.message.message.controller;

import org.springframework.ui.Model;
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
    private String list(Model model) {
        model.addAttribute("page", 1);
        model.addAttribute("size", 20);
        return "message/list";
    }

}
