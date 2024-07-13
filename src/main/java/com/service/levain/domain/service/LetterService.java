package com.service.levain.domain.service;

import com.service.levain.domain.dto.letter.request.ReqDTO;
import com.service.levain.domain.entity.Letter;
import com.service.levain.domain.entity.Member;
import com.service.levain.domain.repository.LetterRepository;
import com.service.levain.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LetterService {

    private final LetterRepository letterRepository;
    private final MemberRepository memberRepository;

    public Letter saveLetter(ReqDTO reqDTO){
        Optional<Member> optionalMember = memberRepository.findById(reqDTO.getUserName());
        Member member = optionalMember.orElseThrow(() -> new RuntimeException("Member not found"));

        Letter letter = new Letter(reqDTO, member);
        return letterRepository.save(letter);
    }
}
