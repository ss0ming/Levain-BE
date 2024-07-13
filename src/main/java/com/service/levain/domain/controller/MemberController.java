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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.service.levain.domain.dto.member.request.PasswordCheckReqDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<?> signUp(@RequestBody List<SignUpReqDto> signUpReqDtos) {
//        memberService.signUp(signUpReqDto);
        for(SignUpReqDto signUpReqDto : signUpReqDtos) {
            memberService.signUp(signUpReqDto);
        }
        return ResponseUtils.createResponse(HttpStatus.OK, "회원 등록 성공");
    }

//    @GetMapping("/search")
//    public ResponseEntity<?> searchMembers(
//            @RequestParam(required = false) String searchType,
//            @RequestParam(required = false) String searchKeyword
//    ) {
//        SearchReqDto searchReqDto = new SearchReqDto(searchType, searchKeyword);
//        return ResponseUtils.createResponse(HttpStatus.OK,"페이지 완료" ,memberService.searchMembers(searchReqDto).getContent());
//
//    }

    @GetMapping
    public ResponseEntity<?> getMembers() {
        return ResponseUtils.createResponse(HttpStatus.OK, "페이지 완료", memberService.getMembers());
    }

    @PutMapping("/password")
    public ResponseEntity<?> updatePassword(@RequestBody PasswordCheckReqDto passwordDto, @AuthenticationPrincipal UserDetails userDetails) {
        memberService.updatePassword(passwordDto, userDetails.getUsername());
        return ResponseUtils.createResponse(HttpStatus.OK, "비밀번호 수정 완료", null);
    }
}
