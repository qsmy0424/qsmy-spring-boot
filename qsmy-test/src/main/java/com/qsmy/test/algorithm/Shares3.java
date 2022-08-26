package com.qsmy.test.algorithm;

/**
 * @author qsmy
 * @time 2022/8/12
 */
public class Shares3 {

    public static void main(String[] args) {
        int[] arr = new int[]{1,2};
        Shares3 shares3 = new Shares3();
        System.out.println(shares3.maxProfit(arr));
    }

    public int maxProfit(int[] prices) {

        /**
         * fst的两个变量的意义与121题相同，就是简单的动态规划更新
         * fstMineprice : 到该天为止第一次买入股票的最小价格（即所有股票价格的最小值）
         * fstMaxprofit: 到该天为止第一次卖出股票的最大收益（即只交易一次的最大收益）
         *
         * sec两个变量的意义要注意，特别是secMinprice，不单纯是股票原价格，而是在此基础上减去第一次收益
         * secMinprice: 到该天为止第二次买入股票的最小价格（第二次买入股票的价格是原股票价格减去第一次买卖收益）
         * secMaxprofit: 到该天为止第二次卖出股票可获得的最大收益
         * 分别对四个变量进行相应的更新, 最后secMaxprofit就是最大
         */
        return 0;

    }

}
