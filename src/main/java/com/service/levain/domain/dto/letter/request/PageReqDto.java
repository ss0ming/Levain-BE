package com.service.levain.domain.dto.letter.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageReqDto {
    private int page;
    private String userName;

}
