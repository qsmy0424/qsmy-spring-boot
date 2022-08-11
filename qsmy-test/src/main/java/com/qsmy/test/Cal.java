package com.qsmy.test;

import java.util.Scanner;

/**
 * @author qsmy
 * @time 2022/6/2
 */
public class Cal {
    public static void main(String[] args) {
        System.out.println(1111);
//        test();
    }

    public static void test() {
        int max = 0;
        int cur = 0;
        String line = "(1+(2*3)+((8)/4))+1";
        // (()(()))
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '(') {
                if (max == cur) {
                    max++;
                }
                cur++;
            } else if (c == ')') {
                cur--;
            }
        }
        System.out.println(max);
    }

    // 约瑟夫
    public static int findThewinner(int n, int k) {
        int pos = 0;
        for (int i = 2; i < n + 1; i++) {
            pos = (pos + k) % i;
        }
    return pos + 1;
    }
}
