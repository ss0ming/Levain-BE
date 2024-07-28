package com.service.levain.domain.dto.letter.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddLetterReqDto {
    private String content;
    private String writer;
    private Long iconId;
    private String receiver;
}
