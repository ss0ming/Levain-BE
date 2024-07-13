package com.service.levain.domain.controller;

import com.service.levain.domain.dto.icons.request.PurchaseIconReqDto;
import com.service.levain.domain.service.IconService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/icons")
public class IconController {

    private final IconService iconService;

    @PostMapping()
    public ResponseEntity<?> purchaseIcon(@RequestBody PurchaseIconReqDto purchaseIconReqDto) {
        System.out.println(purchaseIconReqDto);
        return iconService.purchaseIcon(purchaseIconReqDto);
    }

}
