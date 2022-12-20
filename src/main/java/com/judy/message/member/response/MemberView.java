package com.judy.message.member.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberView {

    private String nickname;

    @Builder
    public MemberView(String nickname) {
        this.nickname = nickname;
    }

}
