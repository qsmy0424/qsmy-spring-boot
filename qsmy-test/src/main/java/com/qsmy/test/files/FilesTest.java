package com.qsmy.test.files;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author qsmy
 */
@Slf4j
public class FilesTest {

    @Test
    public void test1() throws IOException {
        final Path path = Paths.get("C:\\Users\\qsmy\\Desktop\\excel.txt");
        log.info("{}", path);
        final List<String> list = Files.readAllLines(path);
        list.forEach(System.out::println);

        Files.write(path, "qsmy".getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);

        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            lines.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
            bufferedWriter.write("1234");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() throws IOException {
        final Path path = Paths.get("C:\\Users\\qsmy\\Desktop\\excel.txt");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            System.out.println(reader.readLine());
        }

    }

    @Test
    public void test3() throws IOException {

        final RandomAccessFile accessFile = new RandomAccessFile("C:\\Users\\qsmy\\Desktop\\test.txt", "rw");
        final FileChannel channel = accessFile.getChannel();
        channel.position(channel.size());
        channel.write(ByteBuffer.wrap("1abcd ".getBytes(StandardCharsets.UTF_8)));
        accessFile.close();
    }

    @Test
    public void test4() throws IOException {
        final RandomAccessFile accessFile = new RandomAccessFile("C:\\Users\\qsmy\\Desktop\\test.txt", "rw");
        final FileChannel channel = accessFile.getChannel();
        final ByteBuffer allocate = ByteBuffer.allocate(4);
        StringBuilder sb = new StringBuilder();
        int len;
        while ((len = channel.read(allocate)) != -1) {
            allocate.flip();
            sb.append(new String(allocate.array(), 0, len));
        }
        System.out.println(sb);
        accessFile.close();
    }
}
