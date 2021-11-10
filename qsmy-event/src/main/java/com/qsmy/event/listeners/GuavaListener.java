package com.qsmy.event.listeners;

import com.google.common.eventbus.Subscribe;
import com.qsmy.event.event.TestEnent1;

/**
 * @author qsmy
 */
public class GuavaListener {

    @Subscribe
    public void processEvent(TestEnent1 event) {
        System.out.println(event.getName());
        System.out.println(1111);
    }
}
