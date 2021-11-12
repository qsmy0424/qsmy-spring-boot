package com.qsmy.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author qsmy
 */
@EnableScheduling
@SpringBootApplication
public class QsmyTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(QsmyTaskApplication.class, args);
    }

}
