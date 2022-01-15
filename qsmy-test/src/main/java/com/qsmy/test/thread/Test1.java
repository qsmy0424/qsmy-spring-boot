package com.qsmy.test.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @author qsmy
 */
@Slf4j
public class Test1 {

    public static void main(String[] args) {
        LinkedTransferQueue<Integer> transferQueue = new LinkedTransferQueue();

        Thread t1 = new Thread(() -> {
            try {
                transferQueue.transfer(1);
                System.out.println(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(1000L);
                final Integer take = transferQueue.take();
                log.info("aaaa{}", take);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        t1.start();
        t2.start();
    }
}
