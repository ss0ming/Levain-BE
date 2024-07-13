package com.service.levain.domain.dto.member.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class SearchMemberResDto {
    private String username;
    private String nickname;
    private int reward;

    public static SearchMemberResDto of(String username, String nickname, int reward) {
        return new SearchMemberResDto(username, nickname, reward);
    }
}
