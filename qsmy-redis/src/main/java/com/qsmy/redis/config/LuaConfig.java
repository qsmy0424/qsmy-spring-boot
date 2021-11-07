package com.qsmy.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import java.util.List;

/**
 * @author qsmy
 */
@Configuration
public class LuaConfig {

    @Bean
    public DefaultRedisScript<List> defaultRedisScript() {
        DefaultRedisScript<List> redisScript = new DefaultRedisScript<>();
        redisScript.setResultType(List.class);
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("scripts/test.lua")));
        return redisScript;
    }
}
