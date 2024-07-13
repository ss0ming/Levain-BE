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
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/letters")
public class LetterController {
    private final LetterService letterService;

    @PostMapping("")
    public ResponseEntity letterRegist(@RequestBody HashMap<String, Object>requestJsonMap, Authentication authentication){
        String content = (String)requestJsonMap.get("content");
        String writer = (String)requestJsonMap.get("writer");
        int iconNum = (Integer)requestJsonMap.get("iconNum");
        String userName = "tony_kang"; //임시

        ReqDTO reqDTO = new ReqDTO(content, writer, iconNum, userName);
        System.out.println("reqDTO = "+ reqDTO);

        Letter isSuccess = letterService.saveLetter(reqDTO);
        System.out.println("isSuccess = " + isSuccess);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity letterList(@RequestBody PageReqDTO reqDTO, Authentication authentication){
        System.out.println("회원 전체 편지 리스트 조회");

        String userName = "tony_kang";
        Page<Letter> paging = letterService.findAllLetter(reqDTO,userName);
        for(Letter l : paging) {
            System.out.println(l);
            Member m  = l.getMember();
            m = null;
        }
        return new ResponseEntity(paging, HttpStatus.OK);
    }

    @GetMapping("/{letterId}")
    public ResponseEntity viewLetter(@PathVariable("letterId") Long letterId){
        Letter letter = letterService.findOneLetter(letterId);
        return new ResponseEntity<>(letter, HttpStatus.OK);
    }
}
