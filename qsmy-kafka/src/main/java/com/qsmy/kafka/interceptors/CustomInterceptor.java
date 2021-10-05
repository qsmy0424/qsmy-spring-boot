package com.qsmy.kafka.interceptors;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @author qsmy
 * @time 2021/10/3
 */
public class CustomInterceptor implements ProducerInterceptor<String, String> {

    private int errorCount = 0;
    private int successCount = 0;

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        // 创建一个新的record，把时间戳写入到消息体的最前部
        return new ProducerRecord<>(
                record.topic(),
                record.partition(),
                record.timestamp(),
                record.key(),
                System.currentTimeMillis() + "," + record.value()
        );
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        // 该方法会在消息从RecordAccumulator成功发送到Kafka Broker之后，或者在发送过程
        // 中失败时调用。并且通常都是在producer回调逻辑触发之前。onAcknowledgement运行在
        // producer的IO线程中，因此不要在该方法中放入很重的逻辑，否则会拖慢 producer的消息
        // 发送效率 。

        // 统计成功和失败的次数
        if (exception == null) {
            successCount++;
        } else {
            errorCount++;
        }
    }

    @Override
    public void close() {
        // 关闭interceptor，主要用于执行一些资源清理工作

        // 保存结果
        System.out.println("Success:" + successCount);
        System.out.println("Faild:" + errorCount);
    }

    @Override
    public void configure(Map<String, ?> configs) {
        // 获取配置信息和初始化数据时调用
    }
}
