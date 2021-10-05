package com.qsmy.kafka.controller;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaFailureCallback;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qsmy
 */
@RestController
public class ProduceController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public ProduceController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @RequestMapping("/kafka/send")
    public void sed(@RequestParam String topic, @RequestParam String msg) {
        for (int i = 0; i < 10; i++) {
            ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, msg + i);
            future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
                @Override
                public void onFailure(Throwable ex) {
                    System.out.println("onFailure!");
                }

                @Override
                public void onSuccess(SendResult<String, String> result) {
                    System.out.println("onSuccess!");
                }
            });
        }
    }
}
