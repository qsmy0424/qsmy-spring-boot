package com.qsmy.event.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author qsmy
 */
public class TestEvent extends ApplicationEvent {

    private String msg;

    public TestEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
