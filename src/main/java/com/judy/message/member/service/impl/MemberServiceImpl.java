package com.judy.message.member.service.impl;

import com.judy.message.common.response.Result;
import com.judy.message.common.response.SingleResponse;
import com.judy.message.member.entity.Member;
import com.judy.message.member.exception.MemberNotFoundException;
import com.judy.message.member.exception.WrongPasswordException;
import com.judy.message.member.repository.MemberRepository;
import com.judy.message.member.request.MemberJoin;
import com.judy.message.member.request.MemberLogin;
import com.judy.message.member.response.MemberView;
import com.judy.message.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public ResponseEntity<MemberView> join(MemberJoin memberJoin) {
        Member member = memberRepository.join(memberJoin.toMember());
        return ResponseEntity.ok(member.toMemberView());
    }

    @Override
    public ResponseEntity<MemberView> findByNickname(String nickname) {
        Member member = findMemberByNickname(nickname);
        if (ObjectUtils.isEmpty(member)) {
            throw new MemberNotFoundException();
        }
        ResponseEntity response = ResponseEntity.ok(member.toMemberView());
        return response;
    }

    @Override
    public Member findMemberByNickname(String nickname) {
        return memberRepository.findByNickname(nickname);
    }

    @Override
    public ResponseEntity<List<MemberView>> findLikeNickname(String keyword) {
        List<Member> members = memberRepository.findLikeNickname(keyword);
        List<MemberView> memberViews = members.stream().map(m -> m.toMemberView()).collect(Collectors.toList());
        return ResponseEntity.ok(memberViews);
    }

    @Override
    public ResponseEntity<SingleResponse> login(MemberLogin memberLogin) {
        Member member = findMemberByNickname(memberLogin.getNickname());
        if (ObjectUtils.isEmpty(member)) {
            throw new MemberNotFoundException();
        }

        if (!member.getPassword().equals(member.getPassword())) {
            throw new WrongPasswordException();
        }
        SingleResponse response = SingleResponse.builder()
                                                .resultCode(Result.SUCCESS.getResultCode())
                                                .resultMessage(Result.SUCCESS.getResultMessage())
                                                .data(member.toMemberView())
                                                .build();
        return ResponseEntity.ok(response);
    }

}
