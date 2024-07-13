package com.service.levain.domain.controller;

import com.service.levain.domain.dto.letter.request.PageReqDTO;
import com.service.levain.domain.dto.letter.request.ReqDTO;
import com.service.levain.domain.entity.Letter;
import com.service.levain.domain.entity.Member;
import com.service.levain.domain.service.LetterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/letters")
public class LetterController {
    private final LetterService letterService;

    @PostMapping("")
    public ResponseEntity letterRegist(@RequestBody HashMap<String, Object>requestJsonMap, @AuthenticationPrincipal UserDetails userDetails){
        String content = (String)requestJsonMap.get("content");
        String writer = (String)requestJsonMap.get("writer");
        int iconNum = (Integer)requestJsonMap.get("iconNum");
        String receiver = (String)requestJsonMap.get("receiver");//받는이

        ReqDTO reqDTO = new ReqDTO(content, writer, iconNum, receiver, userDetails.getUsername());
        System.out.println("reqDTO = "+ reqDTO);

        Letter isSuccess = letterService.saveLetter(reqDTO);
        System.out.println("isSuccess = " + isSuccess);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{page}")
    public ResponseEntity letterList(@PathVariable("page") int page, @AuthenticationPrincipal UserDetails userDetails){
        System.out.println("회원 전체 편지 리스트 조회");

        String userName = userDetails.getUsername();
        Page<Letter> paging = letterService.findAllLetter(page,userName);
        return new ResponseEntity(paging, HttpStatus.OK);
    }

    @GetMapping("/{letterId}")
    public ResponseEntity viewLetter(@PathVariable("letterId") Long letterId){
        Letter letter = letterService.findOneLetter(letterId);
        return new ResponseEntity<>(letter, HttpStatus.OK);
    }
}
