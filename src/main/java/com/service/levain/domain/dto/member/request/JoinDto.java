package com.service.levain.domain.dto.member.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JoinDto {

    private String userName;
    private String password;
    private String nickname;

    public static JoinDto of(String userName,String password,String nickname){
        return new JoinDto(userName, password, nickname);
    }
}
