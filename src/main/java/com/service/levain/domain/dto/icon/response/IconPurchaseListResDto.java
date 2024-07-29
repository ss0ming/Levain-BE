package com.service.levain.domain.dto.icon.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class IconPurchaseListResDto {
    private Long iconId;
    private String iconName;
    private String iconPath;
    private int price;
    private boolean purchased;

    public static IconPurchaseListResDto of(Long iconId, String iconName, String iconPath, int price, boolean purchased) {
        return new IconPurchaseListResDto(iconId, iconName, iconPath, price, purchased);
    }
}
