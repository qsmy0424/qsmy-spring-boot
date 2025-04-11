package com.qsmy.av;

import cn.hutool.core.io.file.FileNameUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author qsmy
 * @time 2024/4/21
 */

/**
 * 移动图片至文件夹中
 */
@Slf4j
public class Test5 {

    public static void main(String[] args) throws IOException {
        String str = "/Users/qsmy/Pictures/qsmy";
        Files.walk(Path.of(str), 1).forEach(
                path -> {
                    if (!Files.isDirectory(path)) {
                        if (path.toString().contains(" (")) {
                            try {
                                Files.delete(path);
                            } catch (IOException e) {
                                log.error(e.getMessage(), e);
                            }
                        } else {
                            String name = FilenameUtils.getName(path.toString());
                            if (name.contains("-")) {
                                String folder = name.substring(0, name.indexOf("-"));

                                try {
                                    if (!Files.exists(Path.of(str, folder))) {
                                        Path dir = Path.of(str, folder);
                                        Files.createDirectories(dir);
                                    }

                                    Files.move(path, Path.of(str, folder, name));
                                } catch (FileAlreadyExistsException e) {
                                    try {
                                        Files.delete(path);
                                    } catch (IOException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                } catch (IOException e) {
                                    log.error(e.getMessage(), e);
                                }
                            }
                        }

                    }
                }
        );

        // Files.move(Paths.get("/Users/qsmy/Pictures/qsmy/FSDSS-600_detail_6.jpg"), Path.of("/Users/qsmy/Pictures/qsmy", "FSDSS", "FSDSS-600_detail_6.jpg"));


    }
}
