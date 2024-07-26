package com.service.levain.domain.service;

import com.service.levain.domain.dto.member.request.LoginReqDto;
import com.service.levain.domain.dto.member.request.PasswordCheckReqDto;
import com.service.levain.domain.dto.member.request.SignUpReqDto;
import com.service.levain.domain.dto.member.response.MemberResDto;
import com.service.levain.domain.dto.member.response.MembersResDto;
import com.service.levain.domain.entity.Member;
import com.service.levain.domain.repository.MemberRepository;
import com.service.levain.global.auth.jwt.component.JwtTokenProvider;
import com.service.levain.global.auth.jwt.dto.AccessTokenDto;
import com.service.levain.global.auth.jwt.dto.TokenDto;
import com.service.levain.global.exception.CustomException;
import com.service.levain.global.exception.ErrorCode;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    @Transactional(readOnly = true)
    public AccessTokenDto login(LoginReqDto loginReqDto, HttpServletResponse response) {
        // userName으로 Member 조회
        Member savedMember = memberRepository.findById(loginReqDto.userName())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_MEMBER));

        // Password 일치 여부 확인
        if (!passwordEncoder.matches(loginReqDto.password(), savedMember.getPassword())) {
            throw new CustomException(ErrorCode.FAILED_TO_LOGIN);
        }

        // Authentication 객체 생성
        Authentication authentication = new UsernamePasswordAuthenticationToken(savedMember.getUserName(), savedMember.getPassword(), Collections.emptyList());

        // 인증 정보를 기반으로 JWT Token 생성
        TokenDto tokenDto = jwtTokenProvider.generateToken(authentication);

        return tokenDto.accessTokenDto();
    }

    public void signUp(SignUpReqDto signUpReqDto) {
        // 비밀번호 암호화
        signUpReqDto.updatePassword(passwordEncoder.encode(signUpReqDto.getPassword()));

        Member member = Member.createMember(signUpReqDto.getUserName(), signUpReqDto.getPassword(), signUpReqDto.getNickname());
        memberRepository.save(member);
    }


    @Transactional(readOnly = true)
    public List<MembersResDto> getMembers() {
        List<MembersResDto> members = memberRepository.findAll().stream()
                .map(MembersResDto::new)
                .collect(Collectors.toList());

        if (members.isEmpty()) {
            throw new CustomException(ErrorCode.NO_MEMBERS_FOUND);
        }

        return members;
    }

    @Transactional(readOnly = true)
    public MemberResDto getMember(String userName) {
        Member member = memberRepository.findById(userName)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_MEMBER));

        return new MemberResDto(member);
    }

    public void updatePassword(PasswordCheckReqDto passwordCheckReqDto,String userName) {
        if(!passwordCheckReqDto.getNewPassword().equals(passwordCheckReqDto.getNewPasswordCheck())) {
            throw new CustomException(ErrorCode.PASSWORD_MISMATCH);
        }
        Member member = memberRepository.findById(userName)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_MEMBER));
        String password = passwordCheckReqDto.getOldPassword();
        if(!passwordEncoder.matches(password, member.getPassword())) {
            throw new CustomException(ErrorCode.OLD_PASSWORD_MISMATCH);
        }

        String EncodedPassword = passwordEncoder.encode(passwordCheckReqDto.getNewPassword());
        member.updatePassword(EncodedPassword);
        memberRepository.save(member);
    }

}
