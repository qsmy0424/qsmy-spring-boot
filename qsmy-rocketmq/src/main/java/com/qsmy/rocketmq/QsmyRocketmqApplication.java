package com.qsmy.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author qsmy
 */
@Slf4j
@SpringBootApplication
public class QsmyRocketmqApplication implements CommandLineRunner {

    private RocketMQTemplate rocketMQTemplate;

    public QsmyRocketmqApplication(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(QsmyRocketmqApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        SendResult sendResult = rocketMQTemplate.syncSend("qsmy-topic", "wwhm");
        log.info("{}", sendResult);
    }
}
