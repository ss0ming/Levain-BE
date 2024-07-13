package com.service.levain.domain.dto.letter.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageReqDTO {
    private int page;
    private String userName;

}
