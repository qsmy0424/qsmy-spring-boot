package com.qsmy.event.listeners;

import com.qsmy.event.entity.Entity;
import com.qsmy.event.event.TestEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

/**
 * @author qsmy
 */

@Slf4j
@Configuration
public class CustomEventListener {

    @EventListener
    public void listen(TestEvent testEvent) {
        log.info(testEvent.getMsg());
    }

    @EventListener
    public void listen1(Entity entity) {
        log.info("test-----------------" + entity.getName());
    }
}
