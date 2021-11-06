package com.qsmy.nacos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @author qsmy
 */
@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
public class QsmyNacosApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(QsmyNacosApplication.class, args);

        Environment environment = context.getEnvironment();
        log.info("{}", environment);
    }

}
