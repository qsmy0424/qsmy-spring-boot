package com.qsmy.rabbit.work;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author wwhm
 * @time 2023/7/20
 */
public class Work1 {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitUtils.getChannel();
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            String str = new String(message.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + str + "'");
        };
        CancelCallback cancelCallback = consumerTag -> System.out.println("消费者取消消费接口回调逻辑");
        System.out.println("消费者启动等待消费！");
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);
    }
}
