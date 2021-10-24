package com.qsmy.test.thread;

import cn.hutool.core.thread.NamedThreadFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author qsmy
 */
@Slf4j
public class ThreadLocalTest {
    private static final ExecutorService EXECUTOR_SERVICE = new ThreadPoolExecutor(
            8,
            16,
            60,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(20),
            new NamedThreadFactory("thread_local-", false));

    private static final ExecutorService PUSH_EXECUTOR_SERVICE = Executors.newFixedThreadPool(3);

    private static final ThreadLocal<String> USER_THREAD_LOCAL = new ThreadLocal<>();

    private static final ThreadLocal<String> USER_INHERITABLE_THREAD_LOCAL = new InheritableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        // test1();
        test2();
    }

    private static void test1() {
        EXECUTOR_SERVICE.submit(() -> {
            //请求进来时，初始化用户信息
            USER_THREAD_LOCAL.set("李四");
            log.info(Thread.currentThread().getName() + "当前登陆用户：" + USER_THREAD_LOCAL.get());
            USER_THREAD_LOCAL.remove();
        });

        EXECUTOR_SERVICE.submit(() -> {
            //请求进来时，初始化用户信息
            USER_THREAD_LOCAL.set("张三");
            log.info(Thread.currentThread().getName() + "当前登陆用户：" + USER_THREAD_LOCAL.get());
            USER_THREAD_LOCAL.remove();
        });

        EXECUTOR_SERVICE.shutdown();
    }

    private static void test2() throws InterruptedException {
        EXECUTOR_SERVICE.submit(() -> {
            //请求进来时，初始化用户信息
            USER_INHERITABLE_THREAD_LOCAL.set("李四");
            log.info(Thread.currentThread().getName() + "当前登陆用户：" + USER_INHERITABLE_THREAD_LOCAL.get());

            // 接着异步发送短信
            log.info("开始异步发送短信");
            PUSH_EXECUTOR_SERVICE.submit(() -> log.info(Thread.currentThread().getName() + "发送短信给：" + USER_INHERITABLE_THREAD_LOCAL.get()));
            USER_INHERITABLE_THREAD_LOCAL.remove();
        });

        Thread.sleep(1000);
        EXECUTOR_SERVICE.shutdown();
        PUSH_EXECUTOR_SERVICE.shutdown();
    }
}
