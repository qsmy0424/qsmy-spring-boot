package com.qsmy.av;

import cn.hutool.core.io.file.FileNameUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author qsmy
 */
@Slf4j
/**
 * 读取已下载的文件
 */
public class Test4 {

    public static void main(String[] args) throws IOException {
        Files.walk(Paths.get("G:\\wwhm")).forEach(
                path -> {
                    if (!Files.isDirectory(path)) {
                        try {
                            System.out.println(FileNameUtil.getName(path.toString()) + "。" + path + "。" + Files.size(path) / 1024 / 1024);
                        } catch (IOException e) {
                            System.out.println(FileNameUtil.getName(path.toString()) + "。" + path + "。");
                        }
                    }
                }
        );
    }
}
