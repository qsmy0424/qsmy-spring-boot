package com.qsmy.test.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.charset.StandardCharsets;

/**
 * @author qsmy
 */
public class PipeTest {

    public static void main(String[] args) throws IOException {
        final Pipe pipe = Pipe.open();
        final Pipe.SinkChannel sink = pipe.sink();
        final Pipe.SourceChannel source = pipe.source();

        Thread thread1 = new Thread(() -> {
            final ByteBuffer buffer = ByteBuffer.allocate(32);

            String str = "qsmy";
            buffer.put(str.getBytes(StandardCharsets.UTF_8));
            buffer.flip();
            try {
                sink.write(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            final ByteBuffer buffer1 = ByteBuffer.allocate(32);

            int len;
            try {
                while ((len = source.read(buffer1)) != -1) {
                    buffer1.flip();
                    System.out.print(new String(buffer1.array(), 0, len));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
    }
}
