package com.qsmy.nacos.controller;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qsmy
 */
@Slf4j
@RefreshScope
@RestController
public class TestController {

    @Value("${qsmy.test}")
    public String test;

    @GetMapping("/test/{id}")
    public void test(@PathVariable("id") Integer id) {
        log.info("{}---{}", id, test);
    }
}
