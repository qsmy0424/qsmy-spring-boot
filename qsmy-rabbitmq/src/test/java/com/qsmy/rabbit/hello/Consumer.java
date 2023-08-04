package com.qsmy.rabbit.hello;

import com.rabbitmq.client.*;

import java.nio.charset.StandardCharsets;

/**
 * @author wwhm
 * @time 2023/7/14
 */
public class Consumer {
    private final static String QUEUE_NAME = "hello";
    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("qsmy");
        factory.setPassword("qsmy0424");
        factory.setVirtualHost("/qsmy");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        // channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        // 推送的消息如何进行消费的接口回调
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + message + "'");
        };

        // 取消消费的一个回调借口，如在消费的时候队列被删除掉了
        CancelCallback cancelCallback = consumerTag -> System.out.println("消息消费被中断");

        /*
            消费者消费消息
            1.消费哪个队列
            2.消费成功之后是否要自动应答 true 代表自动应答 false 手动应答
            3.消费者成功消费的回调
            4.消费者未成功消费的回调
         */
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);
    }
}
