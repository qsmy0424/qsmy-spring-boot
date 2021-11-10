package com.qsmy.event.config;

import com.qsmy.event.event.TestEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author qsmy
 */
@Component
public class EventPublish {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public EventPublish(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publish(String msg) {
        applicationEventPublisher.publishEvent(new TestEvent(this, msg));
    }
}
