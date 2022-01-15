package com.qsmy.test.rate;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;

/**
 * @author qsmy
 */
public class RateLimiterTest {

    @Test
    public void test1() {
        final RateLimiter rateLimiter = RateLimiter.create(5);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start("1");
        System.out.println(rateLimiter.acquire(10));
        stopWatch.stop();
        stopWatch.start("2");
        System.out.println(rateLimiter.acquire(1));
        stopWatch.stop();
        stopWatch.start("3");
        System.out.println(rateLimiter.acquire(1));
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }

}
