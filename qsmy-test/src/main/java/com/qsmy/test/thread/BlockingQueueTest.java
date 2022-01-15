package com.qsmy.test.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author qsmy
 */
public class BlockingQueueTest {

    public static void main(String[] args) {
        char[] arr1 = "1234567".toCharArray();
        char[] arr2 = "abcdefg".toCharArray();

        BlockingQueue<Character> blockingQueue1 = new ArrayBlockingQueue<>(1);
        BlockingQueue<Character> blockingQueue2 = new ArrayBlockingQueue<>(1);

        Thread thread1 = new Thread(() -> {
            try {
                for (char c : arr1) {
                    System.out.println(c);
                    blockingQueue1.put(c);
                    blockingQueue2.take();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        });
        Thread thread2 = new Thread(() -> {
            try {
                for (char c : arr2) {
                    blockingQueue1.take();
                    blockingQueue2.put(c);
                    System.out.println(c);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        });

        thread1.start();
        thread2.start();
    }
}
