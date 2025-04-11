package com.qsmy.av.controller;

import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.qsmy.av.entity.Info;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wwhm
 * @time 2023/8/4
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class CenterController {

    private final JdbcTemplate jdbcTemplate;

    @PostMapping("save")
    public Object save(@RequestBody Info info) {
        log.info("info: {}", info);

        long id = IdUtil.getSnowflakeNextId();

        List<String> typeList = new ArrayList<>();
        String type = info.getType();
        if (StringUtils.isNotBlank(type) && !"類別:".equals(type)) {
            type = type.replace("\u00A0", "").replace("多選提交", "");
            String[] typeArr = type.split("\\s+");
            typeList.addAll(List.of(typeArr));
            typeList.removeIf(StringUtils::isBlank);
        }

        String author = info.getAuthor();
        if (StringUtils.isNotBlank(author)) {
            author = author.replace("\u00A0", "").replace("♀", "");
            String[] authorArr = author.trim().split("\\s+");
            List<String> authorList = new ArrayList<>();
            for (String s : authorArr) {
                if (StringUtils.isNotBlank(s) && !s.endsWith("♂")) {
                    authorList.add(s);
                }
            }
            author = String.join(",", authorList);
        }

        String score = info.getScore();
        //  4.34分, 由268人評價
        if (StringUtils.isNotBlank(score) && score.contains("分")) {
            score = score.replace("\u00A0", " ");
            score = score.substring(0, score.indexOf("分"));
        }

        String sql = "insert into t_data (id, code, title, author, release_date, type, duration, score) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                id,
                info.getCode().toUpperCase(),
                info.getTitle(),
                author,
                info.getReleaseDate(),
                JSONUtil.toJsonStr(typeList),
                info.getDuration(),
                score);
    }

    @GetMapping("get")
    public Object get(String fileName) {
        return jdbcTemplate.queryForList("select * from t_file where name like ?", "%" + fileName.toUpperCase() + "%");
    }

    @PostMapping("save1")
    public void save1(@RequestBody List<Map<String, Object>> mapList) {
        ZipSecureFile.setMinInflateRatio(0);

        ExcelWriter writer = ExcelUtil.getWriter("/Users/qsmy/Documents/excel.xlsx", "javdb");
        writer.setCurrentRowToEnd();
        writer.write(mapList);
        writer.flush();
        writer.close();
    }

    @PostMapping("saveVideo")
    public void saveVideo(@RequestBody Map<String, String> map) {
        ZipSecureFile.setMinInflateRatio(0);
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
