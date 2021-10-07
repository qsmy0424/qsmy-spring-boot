package com.qsmy.socket.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author qsmy
 * @time 2021/10/7
 */

@Data
@ConfigurationProperties(prefix = "ws.server")
public class WsConfig {

    private String host;
    private Integer port;
}
