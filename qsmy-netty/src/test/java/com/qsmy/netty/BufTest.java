package com.qsmy.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author qsmy
 */
public class BufTest {

    public static void main(String[] args) {
        // 创建byteBuf对象，该对象内部包含一个字节数组byte[10]
        // 通过readerindex和writerIndex和capacity，将buffer分成三个区域
        // 已经读取的区域：[0,readerindex)
        // 可读取的区域：[readerindex,writerIndex)
        // 可写的区域: [writerIndex,capacity)
        ByteBuf buf = Unpooled.buffer(10);
        System.out.println("ByteBuf：" + buf);
        for (int i = 0; i < 8; i++) {
            buf.writeByte(i);
        }
        System.out.println("ByteBuf：" + buf);
        for (int i = 0; i < 5; i++) {
            System.out.println(buf.getByte(i));
        }
        System.out.println("ByteBuf：" + buf);
        for (int i = 0; i < 5; i++) {
            System.out.println(buf.readByte());
        }
        System.out.println("byteBuf=" + buf);

        final ByteBuf byteBuf2 = Unpooled.copiedBuffer("hello,qsmy!", StandardCharsets.UTF_8);
        System.out.println("byteBuf2：" + byteBuf2);
        if (byteBuf2.hasArray()) {
            final byte[] array = byteBuf2.array();
            System.out.println(new String(array, StandardCharsets.UTF_8));
            System.out.println("readerIndex" + byteBuf2.readerIndex());
            System.out.println(byteBuf2.writerIndex());
            System.out.println(byteBuf2.writeBytes("1".getBytes(StandardCharsets.UTF_8)));
            System.out.println(byteBuf2.writerIndex());
            System.out.println(byteBuf2.capacity());
            System.out.println(byteBuf2.getByte(0));
            System.out.println(byteBuf2.readableBytes());
            System.out.println(byteBuf2);

            for (int i = 0, len = byteBuf2.readableBytes(); i < len; i++) {
                System.out.println(((char) byteBuf2.getByte(i)));
            }
            System.out.println(byteBuf2);
        }

        byte[] by = new byte[byteBuf2.readableBytes()];
        byteBuf2.readBytes(by);
        System.out.println("Netty：" + new String(by));
        System.out.println(byteBuf2.getCharSequence(0, 5, CharsetUtil.UTF_8));
        System.out.println(byteBuf2.getCharSequence(6, 5, StandardCharsets.UTF_8));
        System.out.println("//////////////////////////////////////////无耻的分割线//////////////////////////////////////////");
    }
}
