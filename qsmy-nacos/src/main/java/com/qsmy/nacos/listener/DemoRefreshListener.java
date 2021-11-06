package com.qsmy.nacos.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.endpoint.event.RefreshEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author qsmy
 */
@Component
public class DemoRefreshListener implements ApplicationListener<RefreshEvent> {

    Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void onApplicationEvent(RefreshEvent event) {
        log.info("{}", event.getSource());
    }
}
