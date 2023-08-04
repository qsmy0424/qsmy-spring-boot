package com.qsmy.test.algorithm;

import lombok.extern.slf4j.Slf4j;

/**
 * @author wwhm
 * @time 2023/7/25
 */
@Slf4j
public class FibonacciSequence {

    public static void main(String[] args) {
        log.info("{}", fib(5));
        log.info("{}", fib(6));
        log.info("{}", fib(7));
        log.info("{}", fib(8));
    }

    public static int fib(int n) {
        int a = 0;
        int b = 1;

        int result = b;
        while (n > 1) {
            result = a + b;
            a = b;
            b = result;
            n--;
        }
        return result;
    }
}
