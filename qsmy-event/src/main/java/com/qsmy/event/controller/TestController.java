package com.qsmy.event.controller;

import com.google.common.eventbus.EventBus;
import com.qsmy.event.config.EventBusConfig;
import com.qsmy.event.event.TestEvent;
import com.qsmy.event.listeners.GuavaListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qsmy
 */

@RestController
public class TestController {

    @Autowired
    private ApplicationContext applicationContext;

    public static final EventBus EVENTBUS;

    static {
        EVENTBUS = EventBusConfig.getAsyncEventBus();
        EVENTBUS.register(new GuavaListener());
    }

    @GetMapping("/test")
    public void test() {
        applicationContext.publishEvent(new TestEvent(this, "qsmy"));
        EVENTBUS.post(123);
        EVENTBUS.post("123456");
    }
}
