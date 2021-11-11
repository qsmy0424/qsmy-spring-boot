package com.qsmy.event.listeners;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import com.qsmy.event.event.TestGuavaEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author qsmy
 */
@Slf4j
public class GuavaListener {

    @Subscribe
    public void processEvent(TestGuavaEvent event) {
        log.info(event.getName());
    }

    @Subscribe
    @AllowConcurrentEvents
    public void listenerInteger(Integer param) throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        log.info("listenerInteger--{}", param);
    }
    @Subscribe
    public void listenerInteger1(Integer param) {
        log.info("listenerInteger1--{}", param);
    }
    @Subscribe
    public void listenerString(String param) {
        log.info("listenerString--{}", param);
    }

}
