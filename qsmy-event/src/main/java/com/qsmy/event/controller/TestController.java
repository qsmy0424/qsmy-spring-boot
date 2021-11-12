package com.qsmy.event.controller;

import com.qsmy.event.config.EventBusConfig;
import com.qsmy.event.event.TestEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qsmy
 */

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestController {

    private final ApplicationContext applicationContext;

    @GetMapping("/test")
    public void test() {
        applicationContext.publishEvent(new TestEvent(this, "qsmy"));
        EventBusConfig.geEventBus().post("123456");
        EventBusConfig.getAsyncEventBus().post(123);
        EventBusConfig.getAsyncEventBus().post("123456");
    }
}
