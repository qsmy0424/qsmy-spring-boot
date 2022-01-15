package com.qsmy.test.nio;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author qsmy
 */
public class MappedByteBufferTest {

    private final static String CONTENT = "Zero copy implemented by MappedByteBuffer";
    private final static String FILE_NAME = "/mmap.txt";
    private final static String CHARSET = "UTF-8";

    public static void main(String[] args) {
        // writeToFileByMappedByteBuffer();
        readFromFileByMappedByteBuffer();
    }

    public static void writeToFileByMappedByteBuffer() {
        Path path = Paths.get("C:\\Users\\qsmy\\Desktop\\", "excel.txt");
        // Path path = Paths.get(MappedByteBufferTest.class.getResource("/").getPath(), "mmap.txt");
        byte[] bytes = CONTENT.getBytes(Charset.forName(CHARSET));
        try (FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ,
                StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)) {
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, bytes.length);
            if (mappedByteBuffer != null) {
                mappedByteBuffer.put(bytes);
                mappedByteBuffer.force();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFromFileByMappedByteBuffer() {
        Path path = Paths.get("C:\\Users\\qsmy\\Desktop\\", "excel.txt");
        int length = CONTENT.getBytes(Charset.forName(CHARSET)).length;
        try (FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ)) {
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, length);
            if (mappedByteBuffer != null) {
                byte[] bytes = new byte[length];
                mappedByteBuffer.get(bytes);
                String content = new String(bytes, StandardCharsets.UTF_8);
                System.out.println(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
