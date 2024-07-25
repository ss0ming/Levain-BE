package com.service.levain.domain.service;

import com.service.levain.domain.dto.icons.request.PurchaseIconReqDto;
import com.service.levain.domain.entity.Icon;
import com.service.levain.domain.entity.Member;
import com.service.levain.domain.enums.Icons;
import com.service.levain.domain.repository.IconRepository;
import com.service.levain.domain.repository.MemberRepository;
import com.service.levain.domain.utils.Utils;
import com.service.levain.global.common.ResponseUtils;
import com.service.levain.global.exception.CustomException;
import com.service.levain.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IconService {

//    private final IconRepository iconRepository;
//
//    private final MemberRepository memberRepository;
//
//    public ResponseEntity<?> purchaseIcon(PurchaseIconReqDto purchaseIconReqDto, String userName) {
//        Member member = memberRepository.findById(userName)
//                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_MEMBER));
//
//        // 아이콘을 구매할 수 있는지 확인
//        int iconNum = purchaseIconReqDto.getIconNum();
//        int iconPrice = Icons.getPriceByNum(iconNum);
//        if (!Utils.isPossiblePurchase(member.getReward(), iconPrice)) {
//            throw new CustomException(ErrorCode.INSUFFICIENT_REWARDS);
//        }
//
//        Icon icon = Icon.purchaseOf(purchaseIconReqDto, member);
//        iconRepository.save(icon);
//
//        member.updateReward(member.getReward() - iconPrice);
//        memberRepository.save(member);
//
//        return ResponseUtils.createResponse(HttpStatus.OK, "아이콘 구매 성공");
//    }
//
//    public ResponseEntity<?> getIcons(String userName) {
//        Member member = memberRepository.findById(userName)
//                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_MEMBER));
//
//        List<Icon> icons = iconRepository.findIconNumByMember(member);
//
//        List<Integer> iconNums = new ArrayList<>();
//        for (Icon icon : icons) {
//            iconNums.add(icon.getIconNum());
//        }
//
//        return ResponseUtils.createResponse(HttpStatus.OK, "구매 아이콘 목록 조회 성공", iconNums);
//    }
}
