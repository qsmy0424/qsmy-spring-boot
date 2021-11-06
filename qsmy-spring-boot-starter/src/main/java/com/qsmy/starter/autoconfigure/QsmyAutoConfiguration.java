package com.qsmy.starter.autoconfigure;

import com.qsmy.starter.autoconfigure.qsmy.QsmyProperty;
import com.sun.net.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @author qsmy
 */
@Configuration
@EnableConfigurationProperties(QsmyProperty.class)
public class QsmyAutoConfiguration {

    Logger log = LoggerFactory.getLogger(QsmyAutoConfiguration.class);

    @Bean
    // @ConditionalOnBean(HttpServer.class)
    public HttpServer httpServerQsmyServer(QsmyProperty qsmyProperty) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(qsmyProperty.getPort()), 0);
        server.start();
        log.info("server 成功启动，端口为：{}", qsmyProperty.getPort());
        System.out.printf("server 成功启动，端口为：%d", qsmyProperty.getPort());
        return server;
    }
}
