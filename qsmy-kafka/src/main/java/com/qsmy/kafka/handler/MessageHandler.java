package com.qsmy.kafka.handler;

import com.qsmy.kafka.constants.KafkaConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.KafkaListeners;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 消息处理器
 * </p>
 *
 * @author qsmy
 */
@Component
@Slf4j
public class MessageHandler {

    @KafkaListeners({@KafkaListener(topics = KafkaConstants.TOPIC_TEST, containerFactory = "ackContainerFactory"),
            @KafkaListener(topics = KafkaConstants.TOPIC_QSMY, containerFactory = "ackContainerFactory")})
    public void handleMessage(ConsumerRecord<?, ?> consumerRecord, Acknowledgment acknowledgment) {
        try {
            String message = (String) consumerRecord.value();
            log.info("收到消息: {}，offset: {}，partition: {}", message, consumerRecord.offset(), consumerRecord.partition());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            // 手动提交 offset
            acknowledgment.acknowledge();
        }
    }
}
