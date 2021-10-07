package com.qsmy.redis.service.impl;

import com.google.common.collect.Maps;
import com.qsmy.redis.entity.User;
import com.qsmy.redis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author qsmy
 * @time 2021/10/6
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private static final Map<Long, User> MAP = Maps.newConcurrentMap();

    static {
        MAP.put(1L, new User(1L, "user1"));
        MAP.put(2L, new User(2L, "user2"));
        MAP.put(3L, new User(3L, "user3"));
    }

    @CachePut(value = "user", key = "#user.id")
    @Override
    public User saveOrUpdate(User user) {
        MAP.put(user.getId(), user);
        log.info("保存用户【user】= {}", user);
        return user;
    }

    @Cacheable(value = "user", key = "#id")
    @Override
    public User get(Long id) {
        log.info("查询用户【id】= {}", id);
        return MAP.get(id);
    }

    @CacheEvict(value = "user", key = "#id")
    @Override
    public void delete(Long id) {
        MAP.remove(id);
        log.info("删除用户【id】= {}", id);
    }
}
