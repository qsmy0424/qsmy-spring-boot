package com.qsmy.rabbit.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author wwhm
 * @time 2023/7/20
 */
public class RabbitUtils {
    public static Channel getChannel() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("qsmy");
        factory.setPassword("qsmy0424");

        Connection connection = factory.newConnection();
        return connection.createChannel();
    }
}
