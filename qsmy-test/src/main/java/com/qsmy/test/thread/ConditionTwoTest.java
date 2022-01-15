package com.qsmy.test.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author qsmy
 */
public class ConditionTwoTest {

    public static void main(String[] args) {

        final ReentrantLock lock = new ReentrantLock();
        final Condition condition1 = lock.newCondition();
        final Condition condition2 = lock.newCondition();

        char[] arr1 = "1234567".toCharArray();
        char[] arr2 = "abcdefg".toCharArray();
        Thread thread1 = new Thread(new Process(lock, condition1, condition2, arr1));

        Thread thread2 = new Thread(new Process(lock, condition2, condition1, arr2));

        thread1.start();
        thread2.start();

    }

    public static class Process implements Runnable {

        private final ReentrantLock lock;
        private final Condition condition1;
        private final Condition condition2;
        private final char[] arr2;

        public Process(ReentrantLock lock, Condition condition1, Condition condition2, char[] arr2) {
            this.lock = lock;
            this.condition1 = condition1;
            this.condition2 = condition2;
            this.arr2 = arr2;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                for (char c : arr2) {
                    System.out.println(c);
                    condition2.signal();
                    condition1.await();
                }
                condition2.signal();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }
}
