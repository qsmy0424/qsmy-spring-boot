package com.qsmy.test.nio;

import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

/**
 * @author qsmy
 */
public class BufferTest {

    @Test
    public void test1() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        System.out.println("put之前capacity:" + byteBuffer.capacity());
        System.out.println("put之前limit:" + byteBuffer.limit());
        System.out.println("put之前position:" + byteBuffer.position());
        System.out.println("======================");
        byteBuffer.put("qsmy".getBytes());
        System.out.println("put之后capacity:" + byteBuffer.capacity());
        System.out.println("put之后limit:" + byteBuffer.limit());
        System.out.println("put之后position:" + byteBuffer.position());
        System.out.println("======================");
        byteBuffer.flip();
        System.out.println("flip之后capacity:" + byteBuffer.capacity());
        System.out.println("flip之后limit:" + byteBuffer.limit());
        System.out.println("flip之后position:" + byteBuffer.position());
        System.out.println("======================");
        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);
        System.out.println(new String(bytes, 0, bytes.length));
        byteBuffer.clear();
        System.out.println("clear之后capacity:" + byteBuffer.capacity());
        System.out.println("clear之后limit:" + byteBuffer.limit());
        System.out.println("clear之后position:" + byteBuffer.position());
        System.out.println(byteBuffer.get());
    }

    @Test
    public void test2() {

    }
}
