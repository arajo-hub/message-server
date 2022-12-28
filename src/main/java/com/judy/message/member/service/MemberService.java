package com.judy.message.member.service;

import com.judy.message.common.response.SingleResponse;
import com.judy.message.member.entity.Member;
import com.judy.message.member.request.MemberJoin;
import com.judy.message.member.request.MemberLogin;
import com.judy.message.member.response.MemberView;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MemberService {

    ResponseEntity<MemberView> join(MemberJoin memberJoin);

    ResponseEntity<MemberView> findByNickname(String nickname);

    Member findMemberByNickname(String nickname);

    ResponseEntity<List<MemberView>> findLikeNickname(String keyword);

    ResponseEntity<SingleResponse> login(MemberLogin memberLogin);

}
