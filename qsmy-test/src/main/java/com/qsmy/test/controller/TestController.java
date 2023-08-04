package com.qsmy.test.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qsmy
 * @time 2022/8/26
 */
@RestController
public class TestController {

    @Autowired
    private TestComponent testComponent;

    @GetMapping("/test")
    public void test() {
        testComponent.test();
    }
}
