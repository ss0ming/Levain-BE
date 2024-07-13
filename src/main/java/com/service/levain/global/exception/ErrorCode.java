package com.service.levain.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 회원 오류
    PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "MB002", "비밀번호가 일치하지 않습니다."),

    // jwt 오류
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "JW001", "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED,"JW002", "만료된 토큰입니다."),
    UNSUPPORTED_TOKEN(HttpStatus.BAD_REQUEST, "JW003","Unsupported token"),
    EMPTY_TOKEN(HttpStatus.BAD_REQUEST,"JW004", "Empty token"),

    // 아이콘 오류

    // 편지 오류

    // 서버 오류
    SEVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "SV001", "서버 오류");

    private final HttpStatus httpStatus;

    private final String code;

    private final String message;
}
