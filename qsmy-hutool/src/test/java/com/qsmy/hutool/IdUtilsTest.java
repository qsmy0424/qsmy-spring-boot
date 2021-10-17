package com.qsmy.hutool;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.IntStream;

/**
 * @author qsmy
 */
@Slf4j
public class IdUtilsTest {

    @Test
    public void test1() {
        log.info(IdUtil.randomUUID());
        log.info(IdUtil.simpleUUID());
        System.out.println(IdUtil.fastUUID());
        log.info("{}", IdUtil.getSnowflake(1, 2).nextId());
    }
}
