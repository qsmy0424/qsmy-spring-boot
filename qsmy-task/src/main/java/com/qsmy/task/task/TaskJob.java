package com.qsmy.task.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author qsmy
 */
@Slf4j
@Component
public class TaskJob {

    @Scheduled(cron = "0/10 * * * * ?")
    public void job1() {
        log.info("job1--10--:{}", LocalDateTime.now());
    }

    /**
     * 从启动时间开始，间隔 2s 执行
     * 固定间隔时间
     */
    @Scheduled(fixedRate = 3000)
    public void job2() {
        log.info("job2--3--:{}", LocalDateTime.now());
    }

    /**
     * 从启动时间开始，延迟 5s 后间隔 6s 执行
     * 固定等待时间
     */
    @Scheduled(fixedDelay = 6000, initialDelay = 5000)
    public void job3() {
        log.info("job3--2--:{}", LocalDateTime.now());
    }

}
