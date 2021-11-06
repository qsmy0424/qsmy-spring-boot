package com.qsmy.starter.autoconfigure.qsmy;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author qsmy
 */
@ConfigurationProperties(prefix = "qsmy.server")
public class QsmyProperty {

    public static final Integer DEFAULT_PORT = 9999;

    private Integer port = DEFAULT_PORT;

    public static Integer getDefaultPort() {
        return DEFAULT_PORT;
    }

    public Integer getPort() {
        return port;
    }

    public QsmyProperty setPort(Integer port) {
        this.port = port;
        return this;
    }
}
