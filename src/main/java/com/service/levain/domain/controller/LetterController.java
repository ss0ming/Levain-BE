package com.service.levain.domain.controller;

import com.service.levain.domain.dto.letter.request.ReqDTO;
import com.service.levain.domain.entity.Letter;
import com.service.levain.domain.service.LetterService;
import lombok.RequiredArgsConstructor;
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

    @PostMapping
    public ResponseEntity registLetter(@RequestBody HashMap<String, Object>requestJsonMap, Authentication authentication){
        System.out.println("편지 생성 요청 들어옴");

        String content = (String)requestJsonMap.get("content");
        String writer = (String)requestJsonMap.get("writer");
        int iconNum = (Integer)requestJsonMap.get("iconNum");
        String userName = "kangyuseok"; //임시

        ReqDTO reqDTO = new ReqDTO(content, writer, iconNum, userName);
        System.out.println("reqDTO = "+ reqDTO);

        Letter isSuccess = letterService.saveLetter(reqDTO);
        System.out.println("isSuccess = " + isSuccess);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity letterList(@RequestBody HashMap<String, Object>requestJsonMap){
        return null;
    }


}
