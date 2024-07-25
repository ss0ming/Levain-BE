package com.service.levain.domain.controller;

import com.service.levain.domain.dto.letter.request.AddLetterReqDto;
import com.service.levain.domain.dto.letter.request.PageReqDto;
import com.service.levain.domain.entity.Letter;
import com.service.levain.domain.service.LetterService;
import com.service.levain.global.common.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/letters")
public class LetterController {
//
//    private final LetterService letterService;
//
//    /**
//     * 편지 등록 API
//     */
//    @PostMapping
//    public ResponseEntity<?> letterRegister(@RequestBody AddLetterReqDto addLetterReqDto, @AuthenticationPrincipal UserDetails userDetails){
//        letterService.saveLetter(addLetterReqDto, userDetails.getUsername());
//
//        return ResponseUtils.createResponse(HttpStatus.OK, "편지 등록 성공");
//    }
//
//    /**
//     * 편지 목록 조회 API
//     */
//    @GetMapping
//    public ResponseEntity<?> letterAnotherList(PageReqDto pageReqDTO){
//        Page<Letter> paging = letterService.findAllLetter(pageReqDTO.getPage(),pageReqDTO.getUserName());
//
//        return ResponseUtils.createResponse(HttpStatus.OK, "편지 목록 조회 성공", paging);
//    }
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
//
//    /**
//     * 편지 단일 조회 API
//     */
//    @GetMapping("/{letterId}")
//    public ResponseEntity<?> viewLetter(@PathVariable("letterId") Long letterId){
//        Letter letter = letterService.findOneLetter(letterId);
//
//        return ResponseUtils.createResponse(HttpStatus.OK, "편지 단일 조회 성공", letter);
//    }
}
