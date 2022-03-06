package com.qsmy.test.thread;

import java.util.stream.IntStream;

/**
 * @author qsmy
 */
public class ThreadLocalTest2 {
    private static final ThreadLocal<String> THREAD_LOCAL = ThreadLocal.withInitial(() -> Thread.currentThread().getName());

    public static void main(String[] args) {
        IntStream.range(0, 5).forEach(
                i -> new Thread(() -> System.out.println("threadName:" + THREAD_LOCAL.get()), "qsmy-thread-" + i).start()
        );
    }
}
