package com.qsmy.av;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author qsmy
 */
@Slf4j
public class DirectoryUpdate {

    public static void main(String[] args) throws IOException {
        String rootPath = "G:\\类别";

        Multimap<String, String> map = ArrayListMultimap.create();

        Files.walk(Paths.get("G:\\类别")).forEach(
                path -> {
                    if (path.toString().contains(",")) {
                        // System.out.println(path);

                        if (!Files.isDirectory(path)) {
                            map.put(path.getParent().getFileName().toString(), path.getFileName().toString());
                        }

                        /*if (Files.isDirectory(path)) {
                            String[] typeArr = path.getFileName().toString().split(",|\\s+");
                        }
                        if (!Files.exists(Paths.get(rootPath, typeArr))) {

                        }*/
                    }
                }

        );

        System.out.println(map);

        map.forEach(
                (key, value) -> {
                    // System.out.println(key + "  " + value);
                    String[] typeArr = key.split(",|\\s+");
                    for (String type : typeArr) {
                        type = StringUtils.deleteWhitespace(type);
                        type = type.replaceAll("\\u00A0", "");
                        type = type.replaceAll(" ", "");

                        Path path = Paths.get(rootPath, type);
                        try {
                            if (!Files.exists(path)) {
                                Files.createDirectory(path);
                            }
                            Files.createSymbolicLink(Paths.get(rootPath, type, value), Paths.get("G:\\qsmy",value.substring(0, value.indexOf("-")),  value));
                        } catch (IOException e) {
                            log.error("123", e);
                        }
                    }
                }
        );
    }
}
