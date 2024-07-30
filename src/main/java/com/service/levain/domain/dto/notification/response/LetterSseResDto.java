package com.service.levain.domain.dto.notification.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LetterSseResDto {

    private String userName;
    private String receivedTime;
}
