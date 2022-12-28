package com.judy.message.member.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.judy.message.common.response.SingleResponse;
import com.judy.message.member.entity.Member;
import com.judy.message.member.repository.MemberRepository;
import com.judy.message.member.request.MemberJoin;
import com.judy.message.member.request.MemberLogin;
import com.judy.message.member.response.MemberView;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
class MemberControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 필터 추가
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("로그인 성공")
    void login() throws Exception {
        Member member = Member.builder().nickname("홍길동").password("test1234").build();
        memberRepository.join(member);

        MemberLogin login = MemberLogin.builder().nickname("홍길동").password("test1234").build();
        mockMvc.perform(post("/member/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(login)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원가입 성공")
    void join() throws Exception {
        MemberJoin newMember = MemberJoin.builder().nickname("홍길동").password("test1234").build();
        MvcResult mvcResult = mockMvc.perform(post("/member/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newMember)))
                .andExpect(status().isOk())
                .andReturn();

        MemberView memberView = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), MemberView.class);

        assertEquals(newMember.getNickname(), memberView.getNickname());
    }

}