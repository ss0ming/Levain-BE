package com.service.levain.domain.dto.member.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordCheckReqDto {
    private String oldPassword;
    private String newPassword;
    private String newPasswordCheck;

    public PasswordCheckReqDto(String oldPassword, String newPassword, String newPasswordCheck) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.newPasswordCheck = newPasswordCheck;
    }
}
