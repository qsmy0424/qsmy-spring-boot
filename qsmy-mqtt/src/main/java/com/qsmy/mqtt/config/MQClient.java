package com.qsmy.mqtt.config;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.io.IOException;

/**
 * @author qsmy
 */
@Slf4j
public class MQClient {

    private MqttClient client;

    public void connect() {
        String url = "tcp://127.0.0.1:1883";
        try {
            client = new MqttClient(url, "mqttx_560a4570", new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(false);
            options.setConnectionTimeout(60);
            options.setKeepAliveInterval(30);
            options.setAutomaticReconnect(true);
            options.setUserName("qsmy");
            options.setPassword("wwhm".toCharArray());
            options.setWill("iot/will-topic", "off".getBytes(), 2, true);
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    log.info("connectionLost");
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    log.info("messageArrived");
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    log.info("deliveryComplete");
                }
            });
            client.connect(options);
            log.info("客户端连接成功");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public boolean publish(String topic, byte[] data, Integer qos, Boolean retained) {
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setPayload(data);
        mqttMessage.setQos(qos);
        mqttMessage.setRetained(retained);
        MqttTopic mqttTopic = client.getTopic(topic);
        try {
            MqttDeliveryToken token = mqttTopic.publish(mqttMessage);
            log.info("{} publish topic {}", token.getMessage(), token);
            return true;
        } catch (Exception e) {
            log.error("发送异常:{}", e.toString());
            return false;
        }
    }

    public void subscribe(String topic, int qos){
        try {
            client.subscribe(topic, qos);
            log.info("订阅了{}", topic);
        }catch (MqttException e){
            log.error(e.toString());
        }
    }
}
