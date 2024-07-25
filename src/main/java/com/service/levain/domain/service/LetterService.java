package com.service.levain.domain.service;

import com.service.levain.domain.dto.letter.request.AddLetterReqDto;
import com.service.levain.domain.entity.Letter;
import com.service.levain.domain.entity.Member;
import com.service.levain.domain.repository.LetterRepository;
import com.service.levain.domain.repository.MemberRepository;
import com.service.levain.global.exception.CustomException;
import com.service.levain.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LetterService {

    private final LetterRepository letterRepository;
    private final MemberRepository memberRepository;

    public void saveLetter(AddLetterReqDto addLetterReqDto, String userName){
        Member member = memberRepository.findById(addLetterReqDto.getReceiver())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_MEMBER));

        // reward 증가
        memberRepository.incrementReward(userName);

        Letter letter = new Letter(addLetterReqDto, member);
        letterRepository.save(letter);
    }

    public Page<Letter> findAllLetter(int page, String userName){
        Pageable pageable = PageRequest.of(page, 7);
        System.out.println(pageable);
        return letterRepository.findByMemberUserName(userName, pageable);
    }

    public Letter findOneLetter(Long letterId){
        Optional<Letter> optionalLetter = letterRepository.findById(letterId);
        Letter letter = optionalLetter.orElseThrow(() -> new RuntimeException("Letter not found"));
        return letter;
    }
}
