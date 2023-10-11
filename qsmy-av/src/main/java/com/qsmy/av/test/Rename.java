package com.qsmy.av.test;

import cn.hutool.core.io.FileUtil;
import org.apache.commons.io.FilenameUtils;
import org.checkerframework.checker.units.qual.C;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author qsmy
 */
public class Rename {

    public static void main(String[] args) throws IOException {

        String strPath = "G:\\ABCD";
        Pattern pattern = Pattern.compile("(\\w+)-(\\d+)");

        Files.list(Paths.get(strPath)).filter(p -> p.toFile().isFile()).forEach(
                path -> {
                    String name = path.toString();
                    String suffix = FilenameUtils.getExtension(name);
                    name = name.toUpperCase();
                    name = FilenameUtils.getBaseName(name);
                    name = name.replace(".H265", "");
                    name = name.replace(".1080P", "");
                    name = name.replace("1080P ", "");
                    name = name.replace("FREEDL.ORG@", "");
                    name = name.replace("HD-", "");
                    name = name.replace("HD_", "");
                    name = name.replace("HHD800.COM@", "");

                    if (!pattern.matcher(name).matches()) {
                        try {
                            Files.move(path, Paths.get(strPath, "12345", name + "." + suffix), StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        try {
                            Files.move(path, Paths.get(strPath, name + "." + suffix), StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );

    }
}
