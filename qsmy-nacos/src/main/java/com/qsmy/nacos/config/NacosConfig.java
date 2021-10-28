package com.qsmy.nacos.config;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author qsmy
 */
@Configuration
public class NacosConfig {

    @Bean
    @ConditionalOnMissingBean
    public NacosDiscoveryProperties nacosProperties() {
        NacosDiscoveryProperties nacosDiscoveryProperties = new NacosDiscoveryProperties();
        Map<String, String> metadata = nacosDiscoveryProperties.getMetadata();
        metadata.put("startup.time", DateUtil.format(DateUtil.date(), DatePattern.NORM_DATETIME_PATTERN));
        return nacosDiscoveryProperties;
    }
}
