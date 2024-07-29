package com.service.levain.domain.controller;

import com.service.levain.domain.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping()
    public SseEmitter subscribe(@AuthenticationPrincipal UserDetails userDetails) {
        return notificationService.subscribe(userDetails.getUsername());
    }
}
