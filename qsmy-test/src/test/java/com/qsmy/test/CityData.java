package com.qsmy.test;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.nio.charset.StandardCharsets;

/**
 * @author qsmy
 * @time 2022/5/30
 */
public class CityData {

    public static void main(String[] args) {
        String filePath = "/Users/qsmy/Downloads/河南坐标.txt";
        String string = FileUtil.readString(filePath, StandardCharsets.UTF_8);
        JSONArray jsonArray = JSONUtil.parseArray(string);
        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            System.out.printf("%s %s %s", jsonObject.get("adcode"), jsonObject.get("name"), jsonObject.get("center"));
            System.out.println();
            JSONArray subArray = JSONUtil.parseArray(jsonObject.get("sub_districts"));
            for (Object o1 : subArray) {
                System.out.printf("%s %s %s", ((JSONObject) o1).get("adcode"), ((JSONObject) o1).get("name"), ((JSONObject) o1).get("center"));
                System.out.println();
            }
        }
    }
}
