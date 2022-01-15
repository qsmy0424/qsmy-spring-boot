package com.qsmy.test.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author qsmy
 */
public class ConditionOneTest {

    public static void main(String[] args) {
        final ReentrantLock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();
        char[] arr1 = "1234567".toCharArray();
        char[] arr2 = "abcdefg".toCharArray();
        Thread thread1 = new Thread(new Process(lock, condition, arr1));

        Thread thread2 = new Thread(new Process(lock, condition, arr2));

        thread1.start();
        thread2.start();
    }

    public static class Process implements Runnable {
        private final ReentrantLock lock;
        private final Condition condition;
        private final char[] arr;

        public Process(ReentrantLock lock, Condition condition, char[] arr) {
            this.lock = lock;
            this.condition = condition;
            this.arr = arr;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                for (char c : arr) {
                    System.out.println(c);
                    condition.signal();
                    condition.await();
                }
                condition.signal();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }
}
