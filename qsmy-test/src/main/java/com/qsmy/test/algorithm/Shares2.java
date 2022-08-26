package com.qsmy.test.algorithm;

/**
 * @author qsmy
 * @time 2022/8/11
 */
public class Shares2 {

    public static void main(String[] args) {
        Shares2 shares2 = new Shares2();
        int[] arr = new int[]{1, 2, 3, 4, 5};
        System.out.println(shares2.maxProfit(arr));
    }

    public int maxProfit(int[] prices) {
        int count = 0;
        int current = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (current < prices[i]) {
                count += (prices[i] - current);

            }
            current = prices[i];
        }
        return count;
    }
}
