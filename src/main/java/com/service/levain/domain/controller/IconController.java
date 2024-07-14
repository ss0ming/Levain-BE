package com.service.levain.domain.controller;

import com.service.levain.domain.dto.icons.request.PurchaseIconReqDto;
import com.service.levain.domain.service.IconService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/icons")
public class IconController {

    private final IconService iconService;

    @PostMapping()
    public ResponseEntity<?> purchaseIcon(@RequestBody PurchaseIconReqDto purchaseIconReqDto, @AuthenticationPrincipal UserDetails userDetails) {
        return iconService.purchaseIcon(purchaseIconReqDto, userDetails.getUsername());
    }

    @GetMapping()
    public ResponseEntity<?> getIcons(@AuthenticationPrincipal UserDetails userDetails) {
        return iconService.getIcons(userDetails.getUsername());
    }
}
