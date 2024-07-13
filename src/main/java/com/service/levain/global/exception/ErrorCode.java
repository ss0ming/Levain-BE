package com.service.levain.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 회원 오류
    NOT_EXIST_MEMBER(HttpStatus.BAD_REQUEST, "UA001", "존재하지 않는 아이디입니다."),

    // 아이콘 오류
    NOT_EXIST_ICON(HttpStatus.BAD_REQUEST, "IC001", "존재하지 않는 아이콘"),
    INSUFFICIENT_REWARDS(HttpStatus.BAD_REQUEST, "IC002", "리워드 부족"),

    // 편지 오류

    // 서버 오류
    SEVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "SV001", "서버 오류");

    private final HttpStatus httpStatus;

    private final String code;

    private final String message;
}
