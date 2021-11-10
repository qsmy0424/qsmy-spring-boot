package com.qsmy.event.controller;

import com.google.common.eventbus.EventBus;
import com.qsmy.event.event.TestEvent;
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

    @GetMapping("/test")
    public void test() {
        applicationContext.publishEvent(new TestEvent(this, "qsmy"));

        EventBus eventBus = new EventBus();

    }
}
