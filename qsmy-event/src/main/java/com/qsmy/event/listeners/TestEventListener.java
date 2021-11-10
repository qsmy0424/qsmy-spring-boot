package com.qsmy.event.listeners;

import com.qsmy.event.event.TestEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author qsmy
 */
@Slf4j
@Component
public class TestEventListener implements ApplicationListener<TestEvent> {


    @Override
    public void onApplicationEvent(TestEvent event) {
        log.info("{}", event);
    }
}
