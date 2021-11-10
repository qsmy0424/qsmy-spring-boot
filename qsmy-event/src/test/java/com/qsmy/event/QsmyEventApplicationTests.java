package com.qsmy.event;

import com.qsmy.event.config.EventPublish;
import com.qsmy.event.event.TestEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;

@SpringBootTest
class QsmyEventApplicationTests {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private EventPublish eventPublish;

    @Test
    void contextLoads() {
        publisher.publishEvent(new TestEvent(this, "qsmy"));
    }

    @Test
    void test() {
        eventPublish.publish("qqqqq");
    }

}
