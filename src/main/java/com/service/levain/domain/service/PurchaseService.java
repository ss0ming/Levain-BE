package com.service.levain.domain.service;

import com.service.levain.domain.entity.Icon;
import com.service.levain.domain.entity.Member;
import com.service.levain.domain.entity.Purchase;
import com.service.levain.domain.repository.IconRepository;
import com.service.levain.domain.repository.MemberRepository;
import com.service.levain.domain.repository.PurchaseRepository;
import com.service.levain.domain.utils.Utils;
import com.service.levain.global.exception.CustomException;
import com.service.levain.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.service.levain.global.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final MemberRepository memberRepository;
    private final IconRepository iconRepository;
    private final PurchaseRepository purchaseRepository;

    @Transactional
    public void purchaseIcon(String userName, Long iconId) {
        // 멤버 정보 조회
        Member member = memberRepository.findById(userName)
                .orElseThrow(() -> new CustomException(NOT_EXIST_MEMBER));

        // 아이콘 정보 조회
        Icon icon = iconRepository.findById(iconId)
                .orElseThrow(() -> new CustomException(NOT_EXIST_ICON));

        // 아이콘 구매 가능 여부 확인
        if(!Utils.isPossiblePurchase(member.getReward(),icon.getPrice())) {
            throw new CustomException(INSUFFICIENT_REWARDS);
        }

        // 이미 구매한 아이콘인지 확인
        boolean alreadyPurchased = purchaseRepository.existsByMemberAndIcon(member, icon);
        if (alreadyPurchased) {
            throw new CustomException(ErrorCode.ALREADY_PURCHASED);
        }

        member.updateReward(member.getReward() - icon.getPrice());
        memberRepository.save(member);

        //구매 정보 저장
        Purchase purchase = Purchase.createPurchase(member, icon);
        purchaseRepository.save(purchase);
    }
}
