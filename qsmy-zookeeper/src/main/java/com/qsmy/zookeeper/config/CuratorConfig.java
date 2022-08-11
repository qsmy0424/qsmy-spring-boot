package com.qsmy.zookeeper.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author qsmy
 * @time 2021/12/1
 */
@Configuration
public class CuratorConfig {

    @Bean(initMethod = "start")
    public CuratorFramework curatorFramework(WrapperZK wrapperZk) {
        return CuratorFrameworkFactory.newClient(
                wrapperZk.getConnectString(),
                wrapperZk.getSessionTimeoutMs(),
                wrapperZk.getConnectionTimeoutMs(),
                new RetryNTimes(wrapperZk.getRetryCount(), wrapperZk.getElapsedTimeMs()));
    }
}