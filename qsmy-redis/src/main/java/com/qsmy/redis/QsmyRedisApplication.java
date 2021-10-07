package com.qsmy.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author qsmy
 */
@EnableCaching
@SpringBootApplication
public class QsmyRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(QsmyRedisApplication.class, args);
    }

}
