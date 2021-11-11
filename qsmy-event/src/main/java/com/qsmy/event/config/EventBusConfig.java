package com.qsmy.event.config;

import cn.hutool.core.thread.NamedThreadFactory;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author qsmy
 */
public class EventBusConfig {

    private EventBusConfig() {
    }

    private static class EventInstanceHolder {
        private static final EventBus EVENT_BUS = new EventBus();
    }

    private static class AsyncEventInstanceHolder {
        private static final ExecutorService EXECUTOR =
                new ThreadPoolExecutor(
                        16,
                        16,
                        60,
                        TimeUnit.SECONDS,
                        new ArrayBlockingQueue<>(100),
                        new NamedThreadFactory("event-bus-", false));
        private static final EventBus EVENT_BUS = new AsyncEventBus(EXECUTOR);
    }

    public static EventBus geEventBus() {
        return EventInstanceHolder.EVENT_BUS;
    }

    public static EventBus getAsyncEventBus() {
        return AsyncEventInstanceHolder.EVENT_BUS;
    }
}
