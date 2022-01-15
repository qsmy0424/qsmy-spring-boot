package com.qsmy.test.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.Set;

/**
 * @author qsmy
 */

@Slf4j
public class SelectorClientTest {

    public static void main(String[] args) {
        try (SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 6666))) {
            socketChannel.configureBlocking(false);
            Selector selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_READ);

            try (FileChannel fileChannel = FileChannel.open(Paths.get("C:\\Users\\qsmy\\Desktop\\test.txt"), StandardOpenOption.READ)) {
                ByteBuffer buffer = ByteBuffer.allocate(1024);

                while (fileChannel.read(buffer) != -1) {
                    buffer.flip();
                    socketChannel.write(buffer);
                    buffer.clear();
                }

                while (selector.select() > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        if (selectionKey.isReadable()) {
                            log.info("selectionKey.isReadable()");
                            SocketChannel channel = (SocketChannel) selectionKey.channel();
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                            int len;
                            // 直到服务端要返回响应的数据给客户端，客户端在这里接收
                            while ((len = channel.read(byteBuffer)) > 0) {
                                byteBuffer.flip();
                                System.out.println(new String(byteBuffer.array(), 0, len));
                                byteBuffer.clear();
                            }

                            channel.close();
                        } else if (selectionKey.isWritable()) {
                            log.info("selectionKey.isWritable()");
                        } else if (selectionKey.isConnectable()) {
                            log.info("selectionKey.isConnectable()");
                        } else if (selectionKey.isAcceptable()) {
                            log.info("selectionKey.isAcceptable()");
                        }
                        iterator.remove();
                    }
                }
            }
        } catch (IOException ignored) {
        }
    }
}
