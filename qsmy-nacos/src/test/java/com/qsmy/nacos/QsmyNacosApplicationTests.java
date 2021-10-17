package com.qsmy.nacos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QsmyNacosApplicationTests {

    @Value("${test}")
    private String name;

    @Test
    void contextLoads() {
    }

    @Test
    public void test() {
        System.out.println(name);
    }

}
