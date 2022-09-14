package com.qsmy.test.copy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qsmy
 * @time 2022/9/13
 */
public class MapTest {

    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();
        String compute = map.compute("1", (k, v) -> {
            if (v == null) {
                return "2";
            }
            return v;
        });
        System.out.println(compute);

        System.out.println(map);
    }
}
