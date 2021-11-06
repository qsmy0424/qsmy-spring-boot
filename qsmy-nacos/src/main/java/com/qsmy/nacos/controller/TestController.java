package com.qsmy.nacos.controller;

import com.qsmy.nacos.TestProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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

    @Value("${qsmy.test1}")
    public String test1;
    @Value("${qsmy.test3}")
    public String test3;
    @Value("${test1.test}")
    private String test;

    @Autowired
    private TestProperties properties;

    @GetMapping("/test/{id}")
    public String test(@PathVariable("id") Integer id) {
        log.info("{}---{}", id, test1);
        log.info(properties.getTest4());
        log.info(test3);
        log.info(test);
        return test1;
    }
}