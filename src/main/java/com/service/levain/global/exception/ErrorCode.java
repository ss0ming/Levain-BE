package com.service.levain.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 회원 오류

    // 아이콘 오류

    // 편지 오류

    // 서버 오류
    SEVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "SV001", "서버 오류");

    private final HttpStatus httpStatus;

    private final String code;

    private final String message;
}
