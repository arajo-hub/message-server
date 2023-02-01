package com.judy.message.member.service;

import com.judy.message.member.entity.Member;
import com.judy.message.member.repository.MemberRepository;
import com.judy.message.member.request.MemberJoin;
import com.judy.message.member.response.MemberView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입")
    void join() {
        MemberJoin memberJoin = MemberJoin.builder()
                                            .nickname("홍길동")
                                            .password("!Test1234")
                                            .build();
        ResponseEntity<MemberView>  response = memberService.join(memberJoin);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("닉네임으로 회원정보 조회")
    void findByNickname() {
        Member member = Member.builder().nickname("홍길동").password("!Test1234").build();
        memberRepository.join(member);

        String nickname = "홍길동";
        ResponseEntity<MemberView> response = memberService.findByNickname(nickname);
        String memberNickname = response.getBody().getNickname();
        assertEquals(nickname, memberNickname);
    }

    @Test
    @DisplayName("닉네임으로 회원정보 like 검색")
    void findLikeNickname() {
        Member member = Member.builder().nickname("홍길동").password("!Test1234").build();
        memberRepository.join(member);

        String keyword = "길";
        ResponseEntity<List<MemberView>> response = memberService.findLikeNickname(keyword);
        assertEquals(1, response.getBody().size());
    }

}