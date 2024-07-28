package com.service.levain.domain.controller;

import com.service.levain.domain.dto.purchase.request.PurchaseIconReqDto;
import com.service.levain.domain.service.PurchaseService;
import com.service.levain.global.common.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/purchase")
@RequiredArgsConstructor
public class PurchaseController {
    private final PurchaseService purchaseService;

    /*
     * 아이콘 구매하기
     */
    @PostMapping
    public ResponseEntity<?> purchaseIcon(@RequestBody PurchaseIconReqDto purchaseReqDto, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        purchaseService.purchaseIcon(username, purchaseReqDto.getIconId());
        return ResponseUtils.createResponse(HttpStatus.OK, "아이콘 구매 성공");
    }
}
