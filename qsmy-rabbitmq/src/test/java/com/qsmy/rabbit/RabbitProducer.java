package com.qsmy.rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author wwhm
 * @time 2023/7/11
 */
public class RabbitProducer {

    public static final String EXCHANGE_NAME = "exchange_demo";
    public static final String ROUTING_KEY = "routing_key_demo";
    public static final String QUEUE_NAME = "queue_demo";
    public static final String IP = "192.168.132.195";
    public static final int PORT = 5672;

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(IP);
        factory.setPort(PORT);
        factory.setUsername("qsmy");
        factory.setPassword("qsmy0424");
        factory.setVirtualHost("/qsmy");

        try {
            // 创建连接
            Connection connection = factory.newConnection();
            // 创建信道
            Channel channel = connection.createChannel();
            // 创建一个 type="direct"，持久化，非自动删除的交换器
            channel.exchangeDeclare(EXCHANGE_NAME, "direct", true, false, null);
            // 创建一个持久化，非排他的，非自动删除的队列
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            // 将交换器与队列通过路由键绑定
            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);

            String message = "Hello World!";
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            channel.close();
            connection.close();
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }

    }
}
