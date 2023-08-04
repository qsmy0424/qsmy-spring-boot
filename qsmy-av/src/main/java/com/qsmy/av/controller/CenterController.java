package com.qsmy.av.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.qsmy.av.entity.Info;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * @author wwhm
 * @time 2023/8/4
 */
@RestController
public class CenterController {

    private final JdbcTemplate jdbcTemplate;

    public CenterController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/save")
    public Object save(@RequestBody Info info) {
        long id = IdUtil.getSnowflakeNextId();
        System.out.println(id);
        System.out.println(info.getCode());
        System.out.println(info.getTitle());
        System.out.println(info.getAuthor());
        write(info.getTitle());
        System.out.println(JSONUtil.toJsonStr(info.getType()));
        String sql = "insert into t_data (id, code, title, author, release_date, type) " +
                "values(?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                id,
                info.getCode(),
                info.getTitle(),
                info.getAuthor(),
                info.getReleaseDate(),
                JSONUtil.toJsonStr(info.getType()));
    }

    private void write(String title) {
        FileUtil.appendUtf8Lines(Collections.singletonList(title), "/Users/wwhm/Downloads/123.txt");
    }
}
