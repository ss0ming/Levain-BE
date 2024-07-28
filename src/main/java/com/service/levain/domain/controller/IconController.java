package com.service.levain.domain.controller;

import com.service.levain.domain.dto.icon.request.CreateIconReqDto;
import com.service.levain.domain.service.IconService;
import com.service.levain.global.common.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/icons")
public class IconController {

    private final IconService iconService;
//
//    /**
//     * 아이콘 구매 API
//     */
//    @PostMapping
//    public ResponseEntity<?> purchaseIcon(@RequestBody PurchaseIconReqDto purchaseIconReqDto, @AuthenticationPrincipal UserDetails userDetails) {
//        return iconService.purchaseIcon(purchaseIconReqDto, userDetails.getUsername());
//    }
//
//    /**
//     * 구매 아이콘 목록 조회 API
//     */
//    @GetMapping
//    public ResponseEntity<?> getIcons(@AuthenticationPrincipal UserDetails userDetails) {
//        return iconService.getIcons(userDetails.getUsername());
//    }
    @PostMapping
    public ResponseEntity<?> createIcon(CreateIconReqDto createIconReqDto, @AuthenticationPrincipal UserDetails userDetails) {

        String userName = userDetails.getUsername();
        iconService.createIcon(createIconReqDto, userName);

        return ResponseUtils.createResponse(HttpStatus.OK, "아이콘 생성 성공");
    }

    @GetMapping
    public ResponseEntity<?> getIcons(@AuthenticationPrincipal UserDetails userDetails) {
        String userName = userDetails.getUsername();
        return ResponseUtils.createResponse(HttpStatus.OK, "아이콘 조회 성공" ,iconService.getAllIconsWithPurchaseStatus(userName));
    }

}
