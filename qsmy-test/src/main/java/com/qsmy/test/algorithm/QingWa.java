package com.qsmy.test.algorithm;

/**
 * @author wwhm
 * @time 2023/7/25
 */
public class QingWa {
    public static void main(String[] args) {
        System.out.println(cal(2));
    }

    public static int cal(int n) {
        int a = 1;
        int b = 1;
        int result = a;
        while (n > 1) {
            result = a + b;
            a = b;
            b = result;
            n--;
        }
        return result;
    }
}
