package com.qsmy.av.test;

import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author qsmy
 */
public class Move {
    public static void main(String[] args) throws IOException {

        String strPath = "G:\\ABCD";
        Files.list(Paths.get(strPath)).filter(p -> p.toFile().isFile()).forEach(
                path -> {
                    String pathString = path.toString();
                    String baseName = FilenameUtils.getBaseName(pathString);
                    String fileDirectory = baseName.substring(0, baseName.indexOf("-"));

                    if (fileDirectory.length() > 1) {

                        try {
                            if (!Files.exists(Paths.get("G:\\qsmy", fileDirectory))) {
                                Files.createDirectories(Paths.get("G:\\qsmy", fileDirectory));
                            }
                            // Files.move(path, Paths.get("G:\\qsmy", fileDirectory, FilenameUtils.getName(pathString)));

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
    }
}
