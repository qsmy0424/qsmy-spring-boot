package com.qsmy.av;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author qsmy
 */
@Slf4j
public class Test3 {
    public static void main(String[] args) throws IOException {

        Files.walk(Paths.get("G:\\类别")).forEach(
                path -> {
                    if (Files.isDirectory(path) && path.toString().contains(",")) {
                        try {
                            FileUtils.deleteDirectory(path.toFile());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

        );
    }
}
