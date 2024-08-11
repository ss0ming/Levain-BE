package com.service.levain.domain.dto.icon.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class CreateIconReqDto {
    private String iconName;
    private MultipartFile iconImage;
    private int price;
}
