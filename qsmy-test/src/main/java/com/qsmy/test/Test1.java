package com.qsmy.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qsmy
 */
public class Test1 {
    public static void main(String[] args) {
        add(100, 200);

        Map<String, String> map = new HashMap<>();
    }

    private static int add(int a, int b) {
        int c = 0;
        c = a + b;
        return c;
    }
}
