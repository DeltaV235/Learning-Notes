package com.wuyue.design.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author DeltaV235
 * @version 1.0
 * @className RedisTest
 * @description
 * @date 2020/8/16 0:10
 */
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;

    public void getConnection() {
    }
}
