package com.qsmy.test.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author qsmy
 * @time 2023-05-12
 */
@Slf4j
@Component
public class TestComponent {

    @Resource
    private TestService testService;

    public void test() {
        testService.test();
        log.info("component test");
    }
}
