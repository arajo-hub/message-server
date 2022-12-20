package com.judy.message.member.service.impl;

import com.judy.message.member.entity.Member;
import com.judy.message.member.repository.MemberRepository;
import com.judy.message.member.request.MemberJoin;
import com.judy.message.member.response.MemberView;
import com.judy.message.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        return ResponseEntity.ok(member.toMemberView());
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

}
