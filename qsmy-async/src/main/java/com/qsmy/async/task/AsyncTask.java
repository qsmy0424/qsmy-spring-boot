package com.qsmy.async.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

/**
 * @author qsmy
 */
@Slf4j
@Component
public class AsyncTask {

    public void test() {
        IntStream.rangeClosed(1, 100).forEach(this::task);
    }

    @Async("taskExecutor1")
    public void task(int i) {
        log.info("task {} started!", i);
    }
}
