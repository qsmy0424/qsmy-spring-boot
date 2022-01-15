package com.qsmy.test.thread;

import java.io.IOException;

/**
 * @author qsmy
 */
public class WaitNotifyTest {

    public static void main(String[] args) {
        char[] arr1 = "1234567".toCharArray();
        char[] arr2 = "abcdefg".toCharArray();
        Object o = new Object();
        Thread thread1 = new Thread(new Process(o, arr1));
        Thread thread2 = new Thread(new Process(o, arr2));
        thread1.start();
        thread2.start();
    }

    public static class Process implements Runnable {
        private final Object object;
        private final char[] arr;

        public Process(Object o, char[] arr) {
            this.object = o;
            this.arr = arr;
        }

        @Override
        public void run() {
            synchronized (object) {
                for (char c : arr) {
                    System.out.println(c);
                    object.notifyAll();
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                object.notifyAll();
            }
        }
    }
}
