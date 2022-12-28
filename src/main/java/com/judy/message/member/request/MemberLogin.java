package com.judy.message.member.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MemberLogin {

    private String nickname;
    private String password;

    @Builder
    public MemberLogin(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

}
