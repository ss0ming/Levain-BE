package com.service.levain.domain.service;

import com.service.levain.domain.dto.member.request.JoinDto;
import com.service.levain.domain.dto.member.response.MembersResDto;
import com.service.levain.domain.entity.Member;
import com.service.levain.domain.repository.MemberRepository;
import com.service.levain.global.exception.CustomException;
import com.service.levain.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    public void createMember(JoinDto joinDto) {
//        String encodedPassword = bCryptPasswordEncoder.encode(joinDto.getPassword());
//        Member member = Member.createMember(joinDto.getUserName(), encodedPassword, joinDto.getNickname());
//        memberRepository.save(member);
//    }


    @Transactional(readOnly = true)
    public List<MembersResDto> getMembers() {
        return memberRepository.findAll().stream()
                .map(MembersResDto::new)
                .collect(Collectors.toList());
    }

    public void checkPasswordsMatch(String password1, String password2) {
        if(!password1.equals(password2)) {
            throw new CustomException(ErrorCode.PASSWORD_MISMATCH);
        }
    }

//    @Transactional
//    public void checkPassword(String oldPassword) {
//        if(memberRepository.findByPassword(oldPassword) == null) {
//            throw new CustomException(ErrorCode.PASSWORD_MISMATCH);
//        }
//    }
}
