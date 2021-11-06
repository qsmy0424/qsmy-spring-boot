package com.qsmy.nacos;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@Slf4j
@SpringBootTest
class QsmyNacosApplicationTests {

    @Value("${test.qsmy3}")
    private String name;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void test() {
        System.out.println(name);
    }

    @Test
    void test1() {
        // -Dserver.port=18084 -Dspring.application.name=nacos1
        log.info(restTemplate.getForObject("http://nacos2/test/11", String.class));
        log.info(restTemplate.getForObject("http://nacos2/test/12", String.class));
    }

}
