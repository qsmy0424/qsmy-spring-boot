package com.qsmy.redis.service.impl;

import com.qsmy.redis.service.RedisMsg;
import org.springframework.stereotype.Component;

/**
 * @author qsmy
 */
@Component
public class RedisPmpSub implements RedisMsg {
    @Override
    public void receiveMessage(String message) {
        //注意通道调用的方法名要和RedisConfig2的listenerAdapter的MessageListenerAdapter参数2相同
        System.out.println(message);
    }
}
