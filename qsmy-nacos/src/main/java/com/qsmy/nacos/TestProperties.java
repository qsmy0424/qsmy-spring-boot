package com.qsmy.nacos;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author qsmy
 */
@Data
@Component
@ConfigurationProperties(prefix = "qsmy")
public class TestProperties {
    private String test4;
}
