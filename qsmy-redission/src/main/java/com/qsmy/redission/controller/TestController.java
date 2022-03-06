package com.qsmy.redission.controller;

import cn.hutool.core.thread.NamedThreadFactory;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;
import java.util.concurrent.*;

/**
 * @author qsmy
 */
@Slf4j
@AllArgsConstructor
@RestController
public class TestController {

    private final RedissonClient redissonClient;

    @GetMapping("/get/lock")
    public String getLock() {
        final RLock lock = redissonClient.getLock("qsmy");
        lock.lock();
        // 如果业务执行时间超过了10秒，手动释放锁会报错
        // 所以我们如果设置了锁的自动过期时间，则执行业务的时间一定要小于锁的自动过期时间，否则就会报错。
        // lock.lock(10, TimeUnit.SECONDS);
        try {
            log.info("加锁成功，执行后续代码。线程 ID：{}", Thread.currentThread().getId());
            TimeUnit.SECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
            log.info("Finally，释放锁成功。线程 ID：{}", Thread.currentThread().getId());
        }
        return "test lock";
    }

    @GetMapping("/get/read/read/lock")
    public void getReadReadLock() {
        final RReadWriteLock lock = redissonClient.getReadWriteLock("qsmy");
        Thread thread1 = new Thread(() -> {
            lock.readLock().lock();
            try {
                log.info("获得锁线程：{}", Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            } finally {
                lock.readLock().unlock();
                log.info("释放锁线程：{}", Thread.currentThread().getName());
            }
        });
        thread1.start();
        Thread thread2 = new Thread(() -> {
            lock.readLock().lock();
            try {
                log.info("获得锁线程：{}", Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(8);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            } finally {
                lock.readLock().unlock();
                log.info("释放锁线程：{}", Thread.currentThread().getName());
            }
        });
        thread2.start();
    }

    @GetMapping("/get/read/write/lock")
    public void getReadWriteLock() {
        final RReadWriteLock lock = redissonClient.getReadWriteLock("qsmy");
        Thread thread1 = new Thread(() -> {
            lock.readLock().lock();
            try {
                log.info("获得读锁线程：{}", Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            } finally {
                lock.readLock().unlock();
                log.info("释放读锁线程：{}", Thread.currentThread().getName());
            }
        });
        thread1.start();
        Thread thread2 = new Thread(() -> {
            lock.writeLock().lock();
            try {
                log.info("获得写锁线程：{}", Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(8);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            } finally {
                lock.writeLock().unlock();
                log.info("释放写锁线程：{}", Thread.currentThread().getName());
            }
        });
        thread2.start();
    }

    @GetMapping("/test/semaphore")
    public void testSemaphore() {
        System.out.println(123);
        final RSemaphore semaphore = redissonClient.getSemaphore("qsmy-semaphore");
        try {
            semaphore.acquire();
            semaphore.acquire();
            semaphore.acquire();
            System.out.println(456);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

    }
}
