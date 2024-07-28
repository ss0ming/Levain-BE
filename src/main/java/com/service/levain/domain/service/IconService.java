package com.service.levain.domain.service;

import com.service.levain.domain.dto.icon.request.CreateIconReqDto;
import com.service.levain.domain.dto.icon.response.IconPurchaseListResDto;
import com.service.levain.domain.entity.Icon;
import com.service.levain.domain.entity.Member;
import com.service.levain.domain.entity.Purchase;
import com.service.levain.domain.enums.Icons;
import com.service.levain.domain.repository.IconRepository;
import com.service.levain.domain.repository.MemberRepository;
import com.service.levain.domain.repository.PurchaseRepository;
import com.service.levain.domain.utils.Utils;
import com.service.levain.global.exception.CustomException;
import com.service.levain.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.service.levain.global.exception.ErrorCode.FILE_NOT_FOUND;
import static com.service.levain.global.exception.ErrorCode.FILE_UPLOAD_FAILED;

@Service
@RequiredArgsConstructor
@Slf4j
public class IconService {
    private final IconRepository iconRepository;
    private final FileService fileService;
    private final MemberRepository memberRepository;
    private final PurchaseRepository purchaseRepository;

    @Value("${iconImageLocation}")
    private String iconImageLocation;

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


    /**
     * 아이콘 등록
     */

    @Transactional
    public void createIcon(CreateIconReqDto createIconReqDto, String userName) {
        String iconName = createIconReqDto.getIconName();
        int price = createIconReqDto.getPrice();

        if (createIconReqDto.getIconImage() == null || createIconReqDto.getIconImage().isEmpty()) {
            log.error("파일이 존재하지 않음");
            throw new CustomException(FILE_NOT_FOUND);
        }

        Member member = memberRepository.findById(userName)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_EXIST_MEMBER));

        // 아이콘을 등록하기 위한 reward를 확인
        deductRewardIfPossible(member, price);

        String originalImageName = createIconReqDto.getIconImage().getOriginalFilename();

        Icon savedIcon = null;
        try {
            String imgName = fileService.uploadFile(iconImageLocation, originalImageName, createIconReqDto.getIconImage().getBytes());
            String imgUrl = "/img/icon/" + imgName;
            Icon icon = Icon.createIcon(iconName, imgUrl, price);
            savedIcon = iconRepository.save(icon);
        } catch (IOException e) {
            log.error("파일 업로드 중 문제 발생", e);
            throw new CustomException(FILE_UPLOAD_FAILED);
        }


        // 아이콘을 등록한 회원은 기본적으로 아이콘을 지급해줌.
        Purchase purchase = Purchase.createPurchase(member, savedIcon);
        purchaseRepository.save(purchase);
    }

    @Transactional(readOnly = true)
    public List<IconPurchaseListResDto> getAllIconsWithPurchaseStatus(String username) {
        // 전체 아이콘 목록 조회
        List<Icon> icons = iconRepository.findAll();

        // 사용자의 구매 목록 조회
        List<Purchase> purchases = purchaseRepository.findByMemberUserName(username);

        // 구매한 아이콘 아이디 목록
        Set<Long> purchasedIconIds = purchases.stream()
                .map(purchase -> purchase.getIcon().getIconId())
                .collect(Collectors.toSet());

        // 아이콘 목록에 구매 여부 정보 추가
        List<IconPurchaseListResDto> iconDtos = icons.stream()
                .map(icon -> IconPurchaseListResDto.of(icon.getIconId(), icon.getIconName(), icon.getIconPath(), icon.getPrice(), purchasedIconIds.contains(icon.getIconId())))
                .toList();

        return iconDtos;
    }

    //TODO : 아이콘 삭제

    private void deductRewardIfPossible(Member member, int iconPrice) {
        if (!Utils.isPossiblePurchase(member.getReward(), iconPrice)) {
            throw new CustomException(ErrorCode.INSUFFICIENT_REWARDS);
        }
        member.updateReward(member.getReward() - iconPrice);
        memberRepository.save(member);
    }
}
