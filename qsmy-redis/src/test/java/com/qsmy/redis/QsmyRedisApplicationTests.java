package com.qsmy.redis;

import com.qsmy.redis.entity.User;
import com.qsmy.redis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class QsmyRedisApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Autowired
    private DefaultRedisScript<Long> defaultRedisScript;

    @Test
    void contextLoads() {
    }

    /**
     * 测试两次查询
     */
    @Test
    void test1() {
        final User user1 = userService.get(1L);
        User user2 = userService.get(1L);
    }

    /**
     * 先存，再查询，查看日志验证缓存
     */
    @Test
    void test2() {
        System.out.println(userService.saveOrUpdate(new User(4L, "测试中文")));
        User user = userService.get(4L);
    }

    /**
     * 测试删除，查看redis是否存在缓存数据
     */
    @Test
    void test3() {
        // 查询一次，使redis中存在缓存数据
        userService.get(2L);
        // 删除，查看redis是否存在缓存数据
        userService.delete(2L);
    }

    /**
     * 测试 Redis 操作
     */
    @Test
    public void get() {
        // 测试线程安全，程序结束查看redis中count的值是否为1000
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        IntStream.range(0, 1000).forEach(i -> executorService.execute(() -> stringRedisTemplate.opsForValue().increment("count", 1)));

        stringRedisTemplate.opsForValue().set("k1", "v1");
        String k1 = stringRedisTemplate.opsForValue().get("k1");
        log.info("【k1】= {}", k1);

        // 以下演示整合，具体Redis命令可以参考官方文档
        String key = "xkcoding:user:1";
        redisTemplate.opsForValue().set(key, new User(1L, "user1"));
        // 对应 String（字符串）
        User user = (User) redisTemplate.opsForValue().get(key);
        log.info("【user】= {}", user);
    }

    @Test
    void testLua() {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                return null;
            }
        });
    }

}
