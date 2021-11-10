package com.qsmy.test.delay;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.DelayQueue;

/**
 * @author qsmy
 */
@Slf4j
public class DelayTest {

    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayObject> delayQueue = new DelayQueue<>();
        delayQueue.add(new DelayObject("A", 1000L * 10));
        delayQueue.add(new DelayObject("B", 2000L * 10));
        delayQueue.add(new DelayObject("C", 3000L * 10));
        delayQueue.add(new DelayObject("D", 4000L * 10));

        log.info("{}", delayQueue.take());
        log.info("{}", delayQueue.take());
        log.info("{}", delayQueue.take());
        log.info("{}", delayQueue.take());
    }
}
