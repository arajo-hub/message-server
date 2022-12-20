package com.judy.message.member.entity;

import com.judy.message.member.response.MemberView;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    private String nickname;
    @Column
    private String password;

    @Builder
    public Member(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    public MemberView toMemberView() {
        return MemberView.builder()
                            .nickname(nickname)
                            .build();
    }

}
