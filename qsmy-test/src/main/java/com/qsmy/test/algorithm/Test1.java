package com.qsmy.test.algorithm;

/**
 * @author wwhm
 * @time 2023/7/31
 */
public class Test1 {
    public static void main(String[] args) {
        Test1 test1 = new Test1();
        System.out.println(test1.uniquePaths(1, 1));
    }

    public int uniquePaths(int m, int n) {
        if (n == 1 || m == 1) {
            return 1;
        }
        int[][] arr = new int[n][m];

        arr[0][0] = 0;

        for (int i = 1; i < n; i++) {
            arr[i][0] = 1;
        }

        for (int j = 1; j < m; j++) {
            arr[0][j] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                arr[i][j] = arr[i - 1][j] + arr[i][j - 1];
            }
        }
        return arr[n - 1][m - 1];
    }
}
