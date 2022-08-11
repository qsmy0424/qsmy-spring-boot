package com.qsmy.zookeeper.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author qsmy
 * @time 2021/12/1
 */
@Data
@Component
@ConfigurationProperties(prefix = "curator")
public class WrapperZK {
    private int retryCount;
    private int elapsedTimeMs;
    private String connectString;
    private int sessionTimeoutMs;
    private int connectionTimeoutMs;
}