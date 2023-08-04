package com.qsmy.test.algorithm;

/**
 * @author wwhm
 * @time 2023/7/25
 */
public class LeetCode198 {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 7, 9, 3, 1};
        System.out.println(cal(arr));
    }

    public static int cal(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int a = 0;
        int b = 0;
        int c;
        for (int j : nums) {
            c = Math.max(b, a + j);
            a = b;
            b = c;
        }
        return b;
    }
}
