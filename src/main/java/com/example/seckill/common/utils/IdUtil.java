package com.example.seckill.common.utils;

public class IdUtil {

    private static String string = "1234567890abcdefghijklmnopqrstuvwxyz";

    public static String getObjectId(){
        StringBuffer sb = new StringBuffer();
        int len = string.length();
        for (int i = 0; i < 36; i++) {
            sb.append(string.charAt(getRandom(len-1)));
        }
        return sb.toString();
    }

    private static int getRandom(int count) {
        return (int) Math.round(Math.random() * (count));
    }
}
