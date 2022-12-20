package com.judy.message.member.request;

import com.judy.message.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberJoin {

    private String nickname;
    private String password;

    @Builder
    public MemberJoin(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    public Member toMember() {
        return Member.builder()
                .nickname(nickname)
                .password(password)
                .build();
    }

}
