package com.service.levain.domain.service;

import com.service.levain.domain.dto.letter.request.AddLetterReqDto;
import com.service.levain.domain.entity.Icon;
import com.service.levain.domain.entity.Letter;
import com.service.levain.domain.entity.Member;
import com.service.levain.domain.repository.IconRepository;
import com.service.levain.domain.repository.LetterRepository;
import com.service.levain.domain.repository.MemberRepository;
import com.service.levain.domain.repository.PurchaseRepository;
import com.service.levain.global.exception.CustomException;
import com.service.levain.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.service.levain.global.exception.ErrorCode.*;

@RequiredArgsConstructor
@Service
public class LetterService {

    private final LetterRepository letterRepository;
    private final MemberRepository memberRepository;
    private final IconRepository iconRepository;
    private final PurchaseRepository purchaseRepository;

    public void createLetter(AddLetterReqDto addLetterReqDto, String userName) {
        // 글을 작성하는 사용자가 존재하는지 확인
        if (!memberRepository.existsByUserName(userName)) {
            throw new CustomException(NOT_EXIST_MEMBER);
        }

        Icon icon = iconRepository.findById(addLetterReqDto.getIconId())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_ICON));

        // 글을 받는 사용자 정보 가져오기
        Member receiverMember = memberRepository.findById(addLetterReqDto.getReceiver())
                .orElseThrow(() -> new CustomException(RECEIVER_NOT_FOUND));

        // 구매한 아이콘인지 확인
        validateIconPurchased(userName, icon);

        // reward 증가
        memberRepository.incrementReward(userName);

        Letter letter = Letter.createLetter(addLetterReqDto.getWriter(), addLetterReqDto.getContent(), receiverMember, icon);
        letterRepository.save(letter);
    }

    //
//    public Page<Letter> findAllLetter(int page, String userName){
//        Pageable pageable = PageRequest.of(page, 7);
//        System.out.println(pageable);
//        return letterRepository.findByMemberUserName(userName, pageable);
//    }
//
//    public Letter findOneLetter(Long letterId){
//        Optional<Letter> optionalLetter = letterRepository.findById(letterId);
//        Letter letter = optionalLetter.orElseThrow(() -> new RuntimeException("Letter not found"));
//        return letter;
//    }

    // 구매한 아이콘이 맞는지 체크하는 로직
    private void validateIconPurchased(String userName, Icon icon) {
        Member senderMember = memberRepository.findById(userName)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_MEMBER));

        boolean purchased = purchaseRepository.existsByMemberAndIcon(senderMember, icon);
        if (!purchased) {
            throw new CustomException(ICON_NOT_PURCHASED);
        }
    }
}
