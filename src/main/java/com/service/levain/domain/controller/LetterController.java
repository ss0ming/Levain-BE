package com.service.levain.domain.controller;

import com.service.levain.domain.dto.letter.request.AddLetterReqDto;
import com.service.levain.domain.dto.letter.response.LetterResDto;
import com.service.levain.domain.dto.page.response.PageResponse;
import com.service.levain.domain.dto.notification.response.LetterSseResDto;
import com.service.levain.domain.service.LetterService;
import com.service.levain.domain.service.NotificationService;
import com.service.levain.domain.utils.Time;
import com.service.levain.global.common.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/letters")
public class LetterController {

    private final LetterService letterService;

    private final NotificationService notificationService;

    /**
     * 편지 등록 API
     */
    @PostMapping
    public ResponseEntity<?> letterRegister(@RequestBody AddLetterReqDto addLetterReqDto, @AuthenticationPrincipal UserDetails userDetails){
        System.out.println(addLetterReqDto);

        letterService.createLetter(addLetterReqDto, userDetails.getUsername());

        LetterSseResDto letterSseResDto = LetterSseResDto.builder()
                .userName(addLetterReqDto.getReceiver())
                .receivedTime(Time.calculateTime(LocalDateTime.now(ZoneId.of("Asia/Seoul"))))
                .build();

        notificationService.customNotify(addLetterReqDto.getReceiver(), letterSseResDto, "편지가 도착했습니다.", "letter");

        return ResponseUtils.createResponse(HttpStatus.OK, "편지 등록 성공");
    }

    /**
     * 편지 목록 조회 API
     */
    @GetMapping
    public ResponseEntity<?> letterAnotherList(@RequestParam int page,@RequestParam String userName){

        PageResponse<LetterResDto> response = letterService.getLettersByUser(page, userName);

        return ResponseUtils.createResponse(HttpStatus.OK, "편지 목록 조회 성공", response);
    }

//
//    /**
//     * 로그인 한 회원의 편지 목록 조회 API
//     */
//    @GetMapping("/{page}")
//    public ResponseEntity<?> letterList(@PathVariable("page") int page, @AuthenticationPrincipal UserDetails userDetails){
//        String userName = userDetails.getUsername();
//        Page<Letter> paging = letterService.findAllLetter(page, userName);
//
//        return ResponseUtils.createResponse(HttpStatus.OK, "로그인 한 회원의 편지 목록 조회 성공", paging);
//    }

    /**
     * 편지 단일 조회 API
     */
    @GetMapping("/{letterId}")
    public ResponseEntity<?> viewLetter(@PathVariable("letterId") Long letterId){

        return ResponseUtils.createResponse(HttpStatus.OK, "편지 단일 조회 성공", letterService.findOneLetter(letterId));
    }
}
