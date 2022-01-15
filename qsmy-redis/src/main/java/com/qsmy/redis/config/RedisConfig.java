package com.qsmy.redis.config;

import com.qsmy.redis.service.RedisMsg;
import com.qsmy.redis.service.impl.RedisChannelSub;
import com.qsmy.redis.service.impl.RedisPmpSub;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;
import java.time.Duration;
import java.util.Optional;

/**
 * <p>
 * redis配置
 * </p>
 *
 * @author qsmy
 */
@Configuration
public class RedisConfig {

    /**
     * 默认情况下的模板只能支持RedisTemplate<String, String>，也就是只能存入字符串，因此支持序列化
     */
    @Bean
    public RedisTemplate<String, Serializable> redisCacheTemplate(LettuceConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Serializable> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    /**
     * 配置使用注解的时候缓存配置，默认是序列化反序列化的形式，加上此配置则为 json 形式
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        return RedisCacheManager
                .builder(factory)
                .cacheDefaults(getCacheConfigurationWithTtl(null))
                .withCacheConfiguration("", getCacheConfigurationWithTtl(30))
                .build();
    }

    /**
     * Redis消息监听器容器
     */
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //订阅了一个叫pmp和channel 的通道，多通道
        container.addMessageListener(listenerAdapter(new RedisPmpSub()),new PatternTopic("pmp"));
        container.addMessageListener(listenerAdapter(new RedisChannelSub()),new PatternTopic("channel"));
        //这个container 可以添加多个 messageListener
        return container;
    }

    /**
     * 配置消息接收处理类
     * @param redisMsg  自定义消息接收类
     */
    MessageListenerAdapter listenerAdapter(RedisMsg redisMsg) {
        //这个地方 是给messageListenerAdapter 传入一个消息接受的处理器，利用反射的方法调用“receiveMessage”
        //也有好几个重载方法，这边默认调用处理器的方法 叫handleMessage 可以自己到源码里面看
        return new MessageListenerAdapter(redisMsg, "receiveMessage");//注意2个通道调用的方法都要为receiveMessage
    }

    private RedisCacheConfiguration getCacheConfigurationWithTtl(Integer seconds) {
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
                // 配置序列化
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
                // 不缓存null
                .disableCachingNullValues()
                //
                .computePrefixWith(name -> name + ":");
        // 缓存数据保存时间
        Optional.ofNullable(seconds).ifPresent(time -> configuration.entryTtl(Duration.ofSeconds(time)));
        return configuration;
    }
}
