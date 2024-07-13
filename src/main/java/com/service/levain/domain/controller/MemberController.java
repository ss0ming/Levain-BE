package com.service.levain.domain.controller;

import com.service.levain.domain.dto.member.request.JoinDto;
import com.service.levain.domain.dto.member.request.LoginReqDto;
import com.service.levain.domain.dto.member.request.PasswordCheckReqDto;
import com.service.levain.domain.service.MemberService;
import com.service.levain.global.common.ResponseUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

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
    public ResponseEntity<?> join(@RequestBody PasswordCheckReqDto passwordDto) {

        memberService.checkPasswordsMatch(passwordDto.getNewPassword(), passwordDto.getNewPasswordCheck());

        return ResponseUtils.createResponse(HttpStatus.CREATED, "회원가입 완료", null);
    }
}
