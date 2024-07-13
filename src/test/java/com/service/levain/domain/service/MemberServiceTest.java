package com.service.levain.domain.service;

import com.service.levain.domain.dto.member.request.JoinDto;
import com.service.levain.domain.entity.Member;
import com.service.levain.domain.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;
    @Test
    void initializeMember() {
        // Integer 데이터 흐름, Lambda 식 - 함수형 언어의 특징을 활용
        IntStream.rangeClosed(1, 50).forEach(i -> {
            JoinDto joinDto = new JoinDto("test" + i, "1234", "테스트" + i);
            memberService.createMember(joinDto);
        });
    }

}