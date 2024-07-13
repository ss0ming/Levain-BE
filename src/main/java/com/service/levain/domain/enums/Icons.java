package com.service.levain.domain.enums;

import com.service.levain.global.exception.CustomException;
import com.service.levain.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public enum Icons {

    HANLASAN(3, 5),
    MACKEREL(4, 10),
    WIND(5, 15);

    private final int num;
    private final int price;

    Icons(int num, int price) {
        this.num = num;
        this.price = price;
    }

    public static int getPriceByNum(int num) {
        for (Icons icon : Icons.values()) {
            if (icon.getNum() == num) {
                return icon.getPrice();
            }
        }
        throw new CustomException(ErrorCode.NOT_EXIST_ICON);
    }

}
