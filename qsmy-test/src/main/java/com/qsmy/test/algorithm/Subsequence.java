package com.qsmy.test.algorithm;

/**
 * @author qsmy
 * @time 2022/8/23
 */
public class Subsequence {

    public static void main(String[] args) {
        System.out.println(isSubsequence("abc", "ahbgdc"));
        System.out.println(isSubsequence("axc", "ahbgdc"));
    }

    public static boolean isSubsequence(String s, String t) {
        if (s.length() == 0) {
            return true;
        }
        if (t.length() == 0) {
            return false;
        }
        char[] chars1 = s.toCharArray();
        char[] chars2 = t.toCharArray();


        int length1 = chars1.length;
        int length2 = chars2.length;

        int start = 0;
        int index = 0;
        while (start < length2 && index < length1) {
            if (chars1[index] == chars2[start]) {
                index++;
            }
            start++;
        }

        return index == length1;
    }

}
