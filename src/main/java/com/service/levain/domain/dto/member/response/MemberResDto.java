package com.service.levain.domain.dto.member.response;

import com.service.levain.domain.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberResDto {

    private String userName;
    private String nickname;
    private int reward;

    public MemberResDto(Member member) {
        this.userName = member.getUserName();
        this.nickname = member.getNickname();
        this.reward = member.getReward();
    }
}
