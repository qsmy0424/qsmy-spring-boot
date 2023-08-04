package com.qsmy.rabbit;

import org.springframework.util.Base64Utils;

/**
 * @author wwhm
 * @time 2023/7/17
 */
public class Test {

    public static void main(String[] args) {
        String str = "1234";
        System.out.println(str.substring(3));
        byte[] mtIzNDU2s = Base64Utils.decodeFromString("MTIzNDU2");
        System.out.println(new String(mtIzNDU2s));
    }
}
