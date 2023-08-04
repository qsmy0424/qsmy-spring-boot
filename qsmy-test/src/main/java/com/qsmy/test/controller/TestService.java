package com.qsmy.test.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author qsmy
 * @time 2023-05-12
 */
@Slf4j
@Service
public class TestService {

    public void test() {
        log.info("service test");
    }
}
