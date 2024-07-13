package com.service.levain.global.auth.jwt.dto;

import lombok.Builder;

@Builder
public record TokenDto(
        AccessTokenDto accessTokenDto
) {

}
