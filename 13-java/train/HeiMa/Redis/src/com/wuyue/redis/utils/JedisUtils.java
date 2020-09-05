package com.wuyue.redis.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author DeltaV235
 * @version 1.0
 * @className JedisUtils
 * @description JedisPool工具类
 * @date 2020/2/23 23:04
 */
public class JedisUtils {
    private static JedisPool jedisPool;

    static {
        InputStream is = JedisUtils.class.getClassLoader().getResourceAsStream("jedis.properties");
        Properties pro = new Properties();
        try {
            if (is != null) {
                pro.load(is);
            } else
                throw new IOException("InputStream is null");
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(Integer.parseInt(pro.getProperty("maxTotal")));
            config.setMaxIdle(Integer.parseInt(pro.getProperty("maxIdle")));
            jedisPool = new JedisPool(config, pro.getProperty("host"), Integer.parseInt(pro.getProperty("port")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Jedis getResource() {
        return jedisPool.getResource();
    }
}
