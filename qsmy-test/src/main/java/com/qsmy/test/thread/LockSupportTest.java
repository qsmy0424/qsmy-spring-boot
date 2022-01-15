package com.qsmy.test.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * @author qsmy
 */
public class LockSupportTest {

    static Thread t1;
    static Thread t2;

    public static void main(String[] args) {
        char[] arr1 = "1234567".toCharArray();
        char[] arr2 = "abcdefg".toCharArray();
        t1 = new Thread(() -> {
            for (char c : arr1) {
                System.out.println(c);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        });
        t2 = new Thread(() -> {
            for (char c : arr2) {
                LockSupport.park();
                System.out.println(c);
                LockSupport.unpark(t1);
            }
        });

        t1.start();
        t2.start();
    }
}
