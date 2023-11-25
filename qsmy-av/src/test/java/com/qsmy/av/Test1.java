package com.qsmy.av;

import cn.hutool.core.util.StrUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qsmy
 */
public class Test1 {
    public static void main(String[] args) throws IOException {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        String[] str = new String[]{"2", "3"};
        System.out.println(StrUtil.join(",", str));
        // Files.createSymbolicLink(Paths.get("G:\\类别\\家教", "NHDTB-417.mp4"), Paths.get("G:\\qsmy\\NHDTB\\NHDTB-417.mp4"));

        System.out.println("NHDTB-417.mp4".substring(0, "NHDTB-417.mp4".indexOf("-")));
    }
}
