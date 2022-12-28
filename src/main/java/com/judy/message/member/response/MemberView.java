package com.judy.message.member.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberView {

    private String nickname;

    @Builder
    public MemberView(String nickname) {
        this.nickname = nickname;
    }

}
