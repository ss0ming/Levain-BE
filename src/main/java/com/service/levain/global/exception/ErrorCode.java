package com.service.levain.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 회원 오류
    NOT_EXIST_MEMBER(HttpStatus.BAD_REQUEST, "UA001", "존재하지 않는 아이디입니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "UA002", "접근 권한이 없습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "UA003", "로그인이 필요합니다."),
    INVALID_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "UA004", "유효하지 않은 액세스 토큰입니다."),
    EXPIRED_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "UA005", "만료된 액세스 토큰입니다."),
    FAILED_TO_LOGIN(HttpStatus.BAD_REQUEST, "UA006", "로그인에 실패했습니다. 아이디 또는 비밀번호를 확인해주세요."),
    PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "UA007", "비밀번호가 일치하지 않습니다."),
    OLD_PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "UA008", "기존 비밀번호가 일치하지 않습니다."),
    NO_MEMBERS_FOUND(HttpStatus.NOT_FOUND, "UA009", "회원을 찾을 수 없습니다."),

    // jwt 오류
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "JW001", "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED,"JW002", "만료된 토큰입니다."),
    UNSUPPORTED_TOKEN(HttpStatus.BAD_REQUEST, "JW003","Unsupported token"),
    EMPTY_TOKEN(HttpStatus.BAD_REQUEST,"JW004", "Empty token"),

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
