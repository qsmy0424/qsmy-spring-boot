package com.qsmy.test.transaction;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @author qsmy
 */
@Service
public class Service2 {
    private final JdbcTemplate jdbcTemplate;

    public Service2(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void test(Integer num) {
        jdbcTemplate.execute("insert into logs (Num) values (" + num + ")");
    }
}
