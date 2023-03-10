package com.judy.message.member.controller;

import com.judy.message.common.response.SingleResponse;
import com.judy.message.member.request.MemberJoin;
import com.judy.message.member.request.MemberLogin;
import com.judy.message.member.response.MemberView;
import com.judy.message.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity login(HttpSession session, @RequestBody @Valid MemberLogin memberLogin) {
        ResponseEntity<SingleResponse> login = memberService.login(memberLogin);
        if (login.getStatusCode().is2xxSuccessful()) {
            session.setAttribute("nickname", memberLogin.getNickname());
        }
        return login;
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/member/login";
    }

    @PostMapping("/join")
    public ResponseEntity<MemberView> join(@RequestBody @Valid MemberJoin memberJoin) {
        return memberService.join(memberJoin);
    }

}
