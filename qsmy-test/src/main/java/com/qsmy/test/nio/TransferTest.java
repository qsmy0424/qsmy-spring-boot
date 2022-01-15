package com.qsmy.test.nio;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * @author qsmy
 */
public class TransferTest {

    @BeforeEach
    public void setup() {
        Path source = Paths.get("C:\\Users\\qsmy\\Desktop\\excel.txt");
        byte[] bytes = "21哈哈12".getBytes(StandardCharsets.UTF_8);
        try (FileChannel fromChannel = FileChannel.open(source, StandardOpenOption.READ,
                StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)) {
            fromChannel.write(ByteBuffer.wrap(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void transferTo() throws Exception {
        try (FileChannel fromChannel = new RandomAccessFile(
                "C:\\Users\\qsmy\\Desktop\\excel.txt", "rw").getChannel();
             FileChannel toChannel = new RandomAccessFile(
                     "C:\\Users\\qsmy\\Desktop\\excel1.txt", "rw").getChannel()) {
            long position = 0L;
            long offset = fromChannel.size();
            fromChannel.transferTo(position, offset, toChannel);
        }
    }

    @Test
    public void transferFrom() throws Exception {
        try (FileChannel fromChannel = new RandomAccessFile(
                "C:\\Users\\qsmy\\Desktop\\excel.txt", "rw").getChannel();
             FileChannel toChannel = new RandomAccessFile(
                     "C:\\Users\\qsmy\\Desktop\\excel1.txt", "rw").getChannel()) {
            long position = 0L;
            long offset = fromChannel.size();
            toChannel.transferFrom(fromChannel, position, offset);
        }
    }

    @Test
    public void test() {
    }

}
