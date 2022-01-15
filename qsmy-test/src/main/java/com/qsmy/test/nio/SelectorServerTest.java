package com.qsmy.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;

/**
 * @author qsmy
 */
public class SelectorServerTest {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel server = ServerSocketChannel.open();
        server.configureBlocking(false);
        server.bind(new InetSocketAddress(6666));

        Selector selector = Selector.open();

        server.register(selector, SelectionKey.OP_ACCEPT);

        // 轮训地获取选择器上已“就绪”的事件--->只要select()>0，说明已就绪
        while (selector.select() > 0) {
            // 获取当前选择器所有注册的“选择键”(已就绪的监听事件)
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            // 获取已“就绪”的事件，(不同的事件做不同的事)
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();

                // 接收事件就绪
                // a connection was accepted by a ServerSocketChannel.
                if (selectionKey.isAcceptable()) {
                    SocketChannel client = server.accept();
                    client.configureBlocking(false);

                    // 注册到选择器上-->拿到客户端的连接为了读取通道的数据(监听读就绪事件)
                    client.register(selector, SelectionKey.OP_READ);

                    // 读事件就绪 a channel is ready for reading
                } else if (selectionKey.isReadable()) {
                    SocketChannel client = ((SocketChannel) selectionKey.channel());
                    ByteBuffer buffer = ByteBuffer.allocate(1024);

                    try (FileChannel outChannel = FileChannel.open(Paths.get("C:\\Users\\qsmy\\Desktop\\test1.txt"), StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
                        while (client.read(buffer) > 0) {
                            buffer.flip();
                            outChannel.write(buffer);
                            buffer.clear();
                        }

                        buffer.put("has received!!!".getBytes());
                        buffer.flip();
                        client.write(buffer);
                        buffer.clear();

                        client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                // 取消选择键(已经处理过的事件，就应该取消掉了)
                iterator.remove();
            }
        }
    }
}
