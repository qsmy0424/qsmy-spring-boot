package com.qsmy.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author qsmy
 */
@EnableAsync
@SpringBootApplication
public class QsmyAsyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(QsmyAsyncApplication.class, args);
    }

}
