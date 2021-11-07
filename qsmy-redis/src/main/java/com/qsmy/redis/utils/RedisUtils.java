package com.qsmy.redis.utils;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author qsmy
 */
@Component
public class RedisUtils {

    private static final Map<Integer, StringRedisTemplate> REDIS_TEMPLATE_MAP = new ConcurrentHashMap<>(16);

    private final RedisConnectionFactory redisConnectionFactory;

    public RedisUtils(RedisConnectionFactory redisConnectionFactory) {
        this.redisConnectionFactory = redisConnectionFactory;
    }

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.port}")
    private Integer port;


    public StringRedisTemplate getTemplate(Integer db) {

        if (REDIS_TEMPLATE_MAP.containsKey(db)) {
            return REDIS_TEMPLATE_MAP.get(db);
        } else {
            RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
            configuration.setDatabase(db);

            configuration.setHostName(host);

            configuration.setPort(port);
            configuration.setPassword(RedisPassword.of(password));
            LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(configuration);
            BeanUtil.copyProperties(redisConnectionFactory, lettuceConnectionFactory);
            lettuceConnectionFactory.setDatabase(db);
            lettuceConnectionFactory.afterPropertiesSet();
            lettuceConnectionFactory.resetConnection();
            StringRedisTemplate redisTemplate = new StringRedisTemplate(lettuceConnectionFactory);
            REDIS_TEMPLATE_MAP.put(db, redisTemplate);
            return redisTemplate;
        }
    }
}
