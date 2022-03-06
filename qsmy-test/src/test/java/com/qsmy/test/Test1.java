package com.qsmy.test;

/**
 * @author qsmy
 */
public class Test1 {

    public static void main(String[] args) {
        String s = "qsmy";
        final int code = s.hashCode();
        System.out.println(code);
        System.out.println(code ^ 35);
        System.out.println((code ^ 35 ) ^ 35);
    }
}
