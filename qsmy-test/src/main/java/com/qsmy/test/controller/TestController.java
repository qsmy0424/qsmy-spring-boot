package com.qsmy.test.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qsmy
 * @time 2022/8/26
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public Object test() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("name", "111");
        jsonObject.set("2", "222");

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.set("name", "111");
        jsonObject1.set("2", "222");
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(jsonObject);
        jsonArray.add(jsonObject1);
        return jsonArray;
    }
}
