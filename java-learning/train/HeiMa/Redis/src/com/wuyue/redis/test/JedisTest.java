package com.wuyue.redis.test;

import com.wuyue.redis.utils.JedisUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Map;

/**
 * @author DeltaV235
 * @version 1.0
 * @className JedisTest
 * @description 测试Jedis
 * @date 2020/2/23 20:40
 */
public class JedisTest {
    /**
     * @author DeltaV235
     * @date 2020/2/23 20:52
     * @description 测试string数据类型的操作
     */
    @Test
    public void test1() {
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.set("username", "zhangsan");
        jedis.close();
    }

    /**
     * @author DeltaV235
     * @date 2020/2/23 20:53
     * @description 测试键值对的过期时间
     */
    @Test
    public void test2() {
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.setex("username", 20, "zhangsan");
        jedis.close();
    }

    /**
     * @author DeltaV235
     * @date 2020/2/23 20:53
     * @description 测试hash数据类型的设置和查询
     */
    @Test
    public void test3() {
        Jedis jedis = new Jedis();
        jedis.hset("mySet", "name", "吴越");
        jedis.hset("mySet", "age", "18");
        Map<String, String> mySet = jedis.hgetAll("mySet");
        for (String key : mySet.keySet()) {
            System.out.println(key + " : " + mySet.get(key));
        }
        jedis.close();
    }

    /**
     * @author DeltaV235
     * @date 2020/2/23 21:02
     * @description 测试List数据类型
     */
    @Test
    public void test4() {
        Jedis jedis = new Jedis();
        jedis.lpush("myList", "a", "b", "c");
        jedis.rpush("myList", "a", "b", "c");
        List<String> myList = jedis.lrange("myList", 0, -1);
        System.out.println(myList);
        System.out.println("jedis.lpop(\"myList\") = " + jedis.lpop("myList"));
        System.out.println("jedis.rpop(\"myList\") = " + jedis.rpop("myList"));
        jedis.del("myList");
        jedis.close();
    }

    /**
     * @author DeltaV235
     * @date 2020/2/23 22:56
     * @description 测试jedis连接池
     */
    @Test
    public void test5() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        JedisPool pool = new JedisPool(config, "localhost", 6379);
        Jedis resource = pool.getResource();
        resource.set("testData", "hehe");
        resource.close();
    }


    /**
     * @author DeltaV235
     * @date 2020/2/23 23:10
     * @description 测试JedisUtils工具类
     */
    @Test
    public void test6() {
        Jedis resource = JedisUtils.getResource();
        resource.set("Utils", "great");
        resource.close();
    }
}
