package com.qsmy.test.thread;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @author qsmy
 */
public class TransferQueueTest {
    public static void main(String[] args) {
        char[] arr1 = "1234567".toCharArray();
        char[] arr2 = "abcdefg".toCharArray();

        LinkedTransferQueue<Character> transferQueue = new LinkedTransferQueue();

        Thread thread1 = new Thread(() -> {
            try {
                for (char c : arr2) {
                    System.out.println(transferQueue.take());
                    transferQueue.transfer(c);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                for (char c : arr1) {
                    transferQueue.transfer(c);
                    System.out.println(transferQueue.take());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        thread1.start();
        thread2.start();
    }
}
