package com.judy.message.member.controller;

import com.judy.message.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
public class MemberViewController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginView() {
        return "member/login";
    }

}
