package com.example.seckill.common.utils;

import org.springframework.util.DigestUtils;

public class Md5Util {

    /**
     * 用于混淆md5的盐值
     */
    private final static String slat = "hjw2131ADAka123!~h^*&$&!)xkdjls21341wdq";

    public static String getMd5(String killProductId){
        String base = killProductId + "/" +slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
