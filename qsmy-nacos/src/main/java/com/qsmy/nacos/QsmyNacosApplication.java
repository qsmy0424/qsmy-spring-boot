package com.qsmy.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author qsmy
 */
@EnableDiscoveryClient
@SpringBootApplication
public class QsmyNacosApplication {

    public static void main(String[] args) {
        SpringApplication.run(QsmyNacosApplication.class, args);
    }

}
