package com.qsmy.rabbit;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author wwhm
 * @time 2023/7/11
 */
public class RabbitConsumer {

    public static void main(String[] args) throws InterruptedException {
        Address[] addresses = new Address[]{new Address(RabbitProducer.IP, RabbitProducer.PORT)};
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("qsmy");
        factory.setPassword("qsmy0424");
        factory.setVirtualHost("/qsmy");

        try {
            Connection connection = factory.newConnection(addresses);
            Channel channel = connection.createChannel();
            // 设置客户端最多接收未被 ack 的消息的个数
            channel.basicQos(64);
            Consumer consumer = new DefaultConsumer(channel) {
                // 获取消息，并且处理，这个方法类似事件监听，如果有消息，会被自动调用
                @Override
                public void handleDelivery(String consumerTag,
                                           Envelope envelope,
                                           AMQP.BasicProperties properties,
                                           byte[] body) {
                    System.out.println("receive msg:" + new String(body));
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        // 手动ACK
                        channel.basicAck(envelope.getDeliveryTag(), false);
                    } catch (InterruptedException | IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            };
            // 监听队列，第二个参数：是否自动进行消息确认
            channel.basicConsume(RabbitProducer.QUEUE_NAME, true, consumer);
            // 等待回调函数执行完毕之后，关闭资源
            TimeUnit.SECONDS.sleep(5);
            channel.close();
            connection.close();
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
