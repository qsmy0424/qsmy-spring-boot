package com.qsmy.av;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 移除文件夹中的空格
 * @author qsmy
 */
@Slf4j
public class Test2 {

    public static void main(String[] args) throws IOException {
        String rootPath = "G:\\类别";

        Multimap<String, String> map = ArrayListMultimap.create();

        Files.walk(Paths.get("G:\\类别")).forEach(
                path -> {
                    if (!Files.isDirectory(path) && path.toString().contains(" ")) {
                        // System.out.println(path);
                        String fileName = path.getFileName().toString();
                        try {
                            System.out.println(fileName);
                            Files.createSymbolicLink(Paths.get(path.toString().replace(" ", "")), Paths.get("G:\\qsmy",fileName.substring(0, fileName.indexOf("-")),  fileName));
                        } catch (IOException e) {
                            log.error(fileName, e);
                        }
                    }
                }

        );

    }
}
