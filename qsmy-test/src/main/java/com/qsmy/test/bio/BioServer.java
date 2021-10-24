package com.qsmy.test.bio;

import cn.hutool.core.thread.NamedThreadFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @author qsmy
 */
@Slf4j
public class BioServer {
    private static final ExecutorService EXECUTOR_SERVICE = new ThreadPoolExecutor(
            8,
            16,
            60,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(20),
            new NamedThreadFactory("qsmy-", false));

    private static void handleSocket(Socket socket) throws IOException {
        byte[] socketContent = new byte[1024];
        int len;
        while ((len = socket.getInputStream().read(socketContent)) != -1) {
            log.info(
                    "Thread:" + Thread.currentThread().getName() + " receive message:" + new String(socketContent, 0, len));
        }
    }

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8888);
        //noinspection InfiniteLoopStatement
        while (true) {
            Socket socket = serverSocket.accept(); // 此处将阻塞直到连接建立
            EXECUTOR_SERVICE.submit(() -> {
                log.info("New connection!,Submit it to thread:" + Thread.currentThread().getName());
                try {
                    handleSocket(socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}

