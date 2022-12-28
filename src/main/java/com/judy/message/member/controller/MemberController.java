package com.judy.message.member.controller;

import com.judy.message.member.request.MemberJoin;
import com.judy.message.member.request.MemberLogin;
import com.judy.message.member.response.MemberView;
import com.judy.message.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity login(HttpSession session, @RequestBody MemberLogin memberLogin) {
        return memberService.login(memberLogin);
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/member/login";
    }

    @PostMapping("/join")
    public ResponseEntity<MemberView> join(@RequestBody MemberJoin memberJoin) {
        return memberService.join(memberJoin);
    }

}
