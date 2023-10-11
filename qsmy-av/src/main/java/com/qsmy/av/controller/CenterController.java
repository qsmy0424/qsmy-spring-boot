package com.qsmy.av.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.qsmy.av.entity.Info;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
        System.out.println(info);
        long id = IdUtil.getSnowflakeNextId();

        List<String> type = new ArrayList<>();

        if (StringUtils.isNotBlank(info.getType()) && !"類別:".equals(info.getType())) {
            String[] split = info.getType().split("\\s+");
            type.addAll(Arrays.asList(split));
            type.remove("");
        }

        String author = "";
        if (StringUtils.isNotBlank(info.getAuthor())) {
            info.setAuthor(info.getAuthor().replace(" ", "").replace("♀", ""));
            String[] split = info.getAuthor().trim().split("\\s+");
            List<String> authorList = new ArrayList<>(Arrays.asList(split));
            authorList.removeIf(s -> s.endsWith("♂"));
            author = CharSequenceUtil.join(",", authorList);
        }

        String score = info.getScore();
        //  4.34分, 由268人評價
        if (StringUtils.isNotBlank(score)) {
            score = score.replace(" ", "").replace("分", "");
            score = score.substring(0, score.indexOf(","));
        }

        String sql = "insert into t_data (id, code, title, author, release_date, type, duration, score) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                id,
                info.getCode(),
                info.getTitle(),
                author,
                info.getReleaseDate(),
                JSONUtil.toJsonStr(type),
                info.getDuration(),
                score);
    }

    private void write(String title) {
        FileUtil.appendUtf8Lines(Collections.singletonList(title), "/Users/wwhm/Downloads/123.txt");
    }
}
