package com.qsmy.socket;

import com.corundumstudio.socketio.SocketIOServer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author qsmy
 */
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@SpringBootApplication
public class QsmyNettySocketioApplication implements ApplicationRunner {

    private final SocketIOServer socketIOServer;

    public static void main(String[] args) {
        SpringApplication.run(QsmyNettySocketioApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        socketIOServer.start();
        log.info("socket server 启动---");
    }
}
