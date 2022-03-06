package com.qsmy.url.controller;

import com.qsmy.url.entity.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author qsmy
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public Object get() {
        return 111111;
    }

    @GetMapping("/test1")
    public Object get1() {
        return ResponseEntity.builder().data(123).code(200).msg("测试").timestamp(new Date()).build();
    }
    @GetMapping("/service-url1/test1")
    public Object get2() {
        return "/service-url/test1";
    }
}
