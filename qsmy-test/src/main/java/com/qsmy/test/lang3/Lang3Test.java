package com.qsmy.test.lang3;

import org.apache.commons.lang3.StringUtils;

/**
 * @author qsmy
 */
public class Lang3Test {
    public static void main(String[] args) {
        // capitalize:首字母变大写
        // 输出Qsmy
        System.out.println(StringUtils.capitalize("qsmy"));

        // capitalize:首字母变小写
        // 输出qsmy
        System.out.println(StringUtils.uncapitalize("Qsmy"));

        // 缩短至某长度，用...结尾
        // qsm...
        System.out.println(StringUtils.abbreviate("qsmywwhm", 6));

        // 去除字符串中的\n \r
        // 输出qsmy
        System.out.println(StringUtils.chomp("qsmy\n\r\n\r"));

        // 判断一个字符串是否包含另一个字符串
        System.out.println(StringUtils.contains("qsmy", "qs"));
        System.out.println(StringUtils.containsIgnoreCase("qsmy", "QS"));

        // 统计一个字符串在另一个字符串中出现的次数
        System.out.println(StringUtils.countMatches("qsmyqsmy", "qs"));

        // 删除字符串中的所有的空格
        System.out.println(StringUtils.deleteWhitespace("qs my w  w   h m"));

        // 判断字符串是否为数字
        System.out.println(StringUtils.isNumeric("123"));

        // 删除字符串中的某个子串
        // 输出结果：qmyqmy
        System.out.println(StringUtils.remove("qsmyqsmy", "s"));
    }
}
