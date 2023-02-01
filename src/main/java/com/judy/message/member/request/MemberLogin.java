package com.judy.message.member.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@ToString
public class MemberLogin {

    @Size(min = 2, max = 10,
            message = "닉네임은 2자부터 10자까지 가능합니다.")
    private String nickname;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$",
            message = "비밀번호는 최소 8자리에 대소문자 각 1자리, 숫자 1자리로 구성되어야 합니다.")
    private String password;

    @Builder
    public MemberLogin(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

}
