package com.service.levain.domain.controller;

import com.service.levain.domain.dto.member.request.LoginReqDto;
import com.service.levain.domain.dto.member.request.SignUpReqDto;
import com.service.levain.domain.service.MemberService;
import com.service.levain.global.auth.jwt.dto.AccessTokenDto;
import com.service.levain.global.common.ResponseUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReqDto loginReqDto, HttpServletResponse response) {
        AccessTokenDto accessTokenDto = memberService.login(loginReqDto, response);

        return ResponseUtils.createResponse(HttpStatus.OK, "로그인 성공", accessTokenDto);
    }

    @PostMapping()
    public ResponseEntity<?> signUp(@RequestBody SignUpReqDto signUpReqDto) {
        memberService.signUp(signUpReqDto);

        return ResponseUtils.createResponse(HttpStatus.OK, "회원 등록 성공");
    }
}
