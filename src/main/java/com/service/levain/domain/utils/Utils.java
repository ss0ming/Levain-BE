package com.service.levain.domain.utils;

public class Utils {

    private Utils() {
    }

    public static boolean isPossiblePurchase(int reward, int price) {
        if (reward < price) return false;
        return true;
    }
}
