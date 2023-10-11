package com.qsmy.av.test;

import cn.hutool.core.io.FileUtil;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author qsmy
 */
public class Move1 {
    public static void main(String[] args) throws IOException {

        String strPath = "G:\\qsmy";


        FileUtil.readLines("C:\\Users\\qsmy0\\Documents\\OneDrive\\デスクトップ\\list.txt", StandardCharsets.UTF_8).forEach(
                s -> {
                    String[] split = s.split("-");
                    Path path = Paths.get(strPath, split[0], s);

                    // System.out.println(Files.exists(path));
                    if (Files.exists(path)) {
                        try {
                            Files.move(path, Paths.get("G:\\ABCD", s));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        System.out.println(path);
                    }

                    /*if (!Files.exists(path) && !Files.exists(Paths.get("G:\\ABCD", s))) {
                        System.out.println(path);
                    }*/
                }
        );


        /*Files.list(Paths.get(strPath)).filter(p -> p.toFile().isFile()).forEach(
                path -> {
                    String pathString = path.toString();
                    String baseName = FilenameUtils.getBaseName(pathString);
                    String fileDirectory = baseName.substring(0, baseName.indexOf("-"));

                    if (fileDirectory.length() > 1) {

                        try {
                            if (!Files.exists(Paths.get("G:\\qsmy", fileDirectory))) {
                                Files.createDirectories(Paths.get("G:\\qsmy", fileDirectory));
                            }
                            Files.move(path, Paths.get("G:\\qsmy", fileDirectory, FilenameUtils.getName(pathString)));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );*/
    }
}
