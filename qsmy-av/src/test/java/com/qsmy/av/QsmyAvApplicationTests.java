package com.qsmy.av;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.qsmy.av.entity.Info;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Slf4j
@SpringBootTest
class QsmyAvApplicationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void contextLoads() throws IOException {

        Files.createSymbolicLink(Paths.get("G:\\ABCD\\12345\\109IENFH-083.mp4"), Paths.get("G:\\ABCD\\109IENFH-083.mp4"));
    }

    @Test
    void move() throws IOException {
        String strPath = "G:\\ABCD";

        String newDirectory = "G:\\qsmy";

        RowMapper<Info> rowMapper = new BeanPropertyRowMapper<>(Info.class);

        Files.list(Paths.get(strPath)).filter(p -> p.toFile().isFile()).forEach(
                path -> {
                    String pathString = path.toString();
                    String baseName = FilenameUtils.getBaseName(pathString);
                    String fileDirectory = baseName.substring(0, baseName.indexOf("-"));
                    String name = FilenameUtils.getName(pathString);

                    if (fileDirectory.length() > 1) {

                        try {

                            Path path1 = Paths.get(newDirectory, fileDirectory);
                            if (!Files.exists(path1)) {
                                Files.createDirectories(path1);
                            }

                            Path finalPath = Paths.get("G:\\qsmy", fileDirectory, name);

                            List<Info> list = jdbcTemplate.query("select * from t_data where code = ?", rowMapper, baseName);


                            if (!list.isEmpty()) {
                                Files.move(path, finalPath);
                                Info info = list.get(0);

                                List<String> typeList = JSONUtil.toList(info.getType(), String.class);

                                log.info("{}  类别：{}", name, typeList);
                                for (Object type : typeList) {

                                    Path categoryPath = Paths.get(newDirectory, "类别", type.toString());
                                    if (!Files.exists(categoryPath)) {
                                        Files.createDirectories(categoryPath);
                                    }

                                    try {
                                        if (!Files.exists(Paths.get(categoryPath.toString(), name))) {
                                            Files.createSymbolicLink(Paths.get(categoryPath.toString(), name), finalPath);
                                        }
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                }


                                String authorStr = info.getAuthor();
                                String[] authorArr = authorStr.split(",|\\s+");

                                log.info("{}  演员：{}", name, Arrays.toString(authorArr));

                                for (String author : authorArr) {
                                    author = author.strip();
                                    Path authorPath = Paths.get(newDirectory, "演员", author);
                                    if (!Files.exists(authorPath)) {
                                        Files.createDirectories(authorPath);
                                    }

                                    if (!Files.exists(Paths.get(authorPath.toString(), name))) {
                                        Files.createSymbolicLink(Paths.get(authorPath.toString(), name), finalPath);
                                    }
                                }
                            } else {
                                log.info("qsmy-{}", baseName);
                            }

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
    }

    @Test
    void test1() {
        RowMapper<Info> rowMapper = new BeanPropertyRowMapper<>(Info.class);
        List<Info> list = jdbcTemplate.query("select * from t_data where code = ?", rowMapper, "T28-643");
        Info info = list.get(0);
        String author = info.getAuthor();
        author = author.strip();
        author = author.replace("\n", "");
        String[] split = author.split(",|\\s+");
        System.out.println(Arrays.toString(split));
    }

}
