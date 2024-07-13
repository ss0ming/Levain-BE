package com.service.levain.domain.dto.member.response;

import com.service.levain.domain.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MembersResDto {
    private String userName;
    private String nickname;

    public MembersResDto(Member member) {
        this.userName = member.getUserName();
        this.nickname = member.getNickname();
    }
}
