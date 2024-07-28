package com.service.levain.domain.dto.member.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentPasswordCheckReqDto {
    private String oldPassword;
}
