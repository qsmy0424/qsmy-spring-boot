package com.qsmy.av;

import cn.hutool.core.util.StrUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qsmy
 */
public class Test1 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        String[] str = new String[]{"2", "3"};
        System.out.println(StrUtil.join(",", str));
    }
}
