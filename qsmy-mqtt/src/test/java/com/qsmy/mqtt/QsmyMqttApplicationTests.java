package com.qsmy.mqtt;

import com.qsmy.mqtt.config.MQClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class QsmyMqttApplicationTests {

    @Test
    void contextLoads() throws IOException {
        MQClient mqClient = new MQClient();
        mqClient.connect();


        mqClient.publish("qsmy", "qsmy1122".getBytes(), 1, true);
        System.in.read();
    }

    @Test
    void subscribe() throws IOException {
        MQClient mqClient = new MQClient();
        mqClient.connect();


        mqClient.subscribe("qsmy", 1);
        System.in.read();
    }

}
