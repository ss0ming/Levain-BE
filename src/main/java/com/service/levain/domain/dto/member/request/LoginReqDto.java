package com.service.levain.domain.dto.member.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class LoginReqDto {
    private String username;
    private String password;

    public static LoginReqDto of(String username, String password) {
        return new LoginReqDto(username, password);
    }
}
