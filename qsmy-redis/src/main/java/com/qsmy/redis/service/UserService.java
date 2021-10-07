package com.qsmy.redis.service;

import com.qsmy.redis.entity.User;

/**
 * @author qsmy
 * @time 2021/10/6
 */
public interface UserService {

    User saveOrUpdate(User user);
    User get(Long id);
    void delete(Long id);
}
