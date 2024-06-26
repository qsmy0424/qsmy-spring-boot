package com.qsmy.av.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.qsmy.av.entity.Info;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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
            String[] split = info.getType().replace(" ", "").replace("多選提交", "").split("\\s+");
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

    @GetMapping("/get")
    public Object get(String fileName) {
        return jdbcTemplate.queryForList("select * from t_file where name like ?", "%" + fileName.toUpperCase() + "%");
    }

    @PostMapping("/save1")
    public void save1(@RequestBody List<Map<String, Object>> mapList) {
        ExcelWriter writer = ExcelUtil.getWriter("/Users/qsmy/Documents/excel.xlsx", "javdb");
        writer.setCurrentRowToEnd();
        writer.write(mapList);
        writer.flush();
        writer.close();
    }

    @PostMapping("/saveVideo")
    public void saveVideo(@RequestBody Map<String, String> map) {
        ExcelWriter writer = ExcelUtil.getWriter("/Users/qsmy/Documents/excel.xlsx", "video");
        writer.setCurrentRowToEnd();
        // Map<String, Object> map = new HashMap<>();
        // map.put("code", name);
        // map.put("url", url);
        String url = map.get("url");
        if (StringUtils.isNotBlank(url)) {
            if (url.startsWith("//")) {
                url = "https:" + url;
            }
            map.put("url", url);
        }
        map.put("name", FileNameUtil.getName(map.get("url")));
        writer.write(List.of(map));
        writer.flush();
        writer.close();
    }

    private void write(String title) {
        // FileUtil.appendUtf8Lines(Collections.singletonList(title), "/Users/wwhm/Downloads/123.txt");

    }
}
