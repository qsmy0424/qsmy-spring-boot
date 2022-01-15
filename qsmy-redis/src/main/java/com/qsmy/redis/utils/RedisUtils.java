package com.qsmy.redis.utils;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author qsmy
 */
@Component
public class RedisUtils {

    private static final Map<Integer, StringRedisTemplate> REDIS_TEMPLATE_MAP = new ConcurrentHashMap<>(16);
    public static final Integer DEFAULT_TIME_OUT = 10;

    private final RedisConnectionFactory redisConnectionFactory;
    private final RedisTemplate<String, String> redisTemplate;

    public RedisUtils(RedisConnectionFactory redisConnectionFactory, RedisTemplate<String, String> redisTemplate) {
        this.redisConnectionFactory = redisConnectionFactory;
        this.redisTemplate = redisTemplate;
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
            StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(lettuceConnectionFactory);
            REDIS_TEMPLATE_MAP.put(db, stringRedisTemplate);
            return stringRedisTemplate;
        }
    }

    public boolean tryLock(String lockKey, String lockValue) {
        DefaultRedisScript<Long> lockRedisScript = new DefaultRedisScript<>();
        lockRedisScript.setLocation(new ClassPathResource("scripts/lock.lua"));
        lockRedisScript.setResultType(Long.class);
        Long result = redisTemplate.execute(lockRedisScript, Collections.singletonList(lockKey), lockValue, DEFAULT_TIME_OUT);
        return Objects.equals(result, 1L);
    }

    public boolean tryUnLock(String lockKey, String lockValue) {
        DefaultRedisScript<Long> unLockRedisScript = new DefaultRedisScript<>();
        unLockRedisScript.setLocation(new ClassPathResource("scripts/unLock.lua"));
        unLockRedisScript.setResultType(Long.class);
        Long result = redisTemplate.execute(unLockRedisScript, Collections.singletonList(lockKey), lockValue, DEFAULT_TIME_OUT);
        return Objects.equals(result, 1L);
    }
}
