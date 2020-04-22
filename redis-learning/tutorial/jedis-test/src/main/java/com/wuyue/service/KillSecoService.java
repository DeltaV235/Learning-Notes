package com.wuyue.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * @author DeltaV235
 * @version 1.0
 * @className KillSecoService
 * @description
 * @date 2020/4/22 2:50
 */
@Service
public class KillSecoService {
    private final JedisPool pool;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public KillSecoService(JedisPool pool) {
        this.pool = pool;
    }

    public boolean doKillSeco(Integer uid, Integer pid) {
        Jedis jedis = pool.getResource();
        String stockKey = "kill_seco_prod:" + pid + ":stock";
        String userKey = "kill_seco_user:" + pid + ":uid";
        if (jedis.get(stockKey) == null) {
            logger.warn("抢购未开始");
            jedis.close();
            return false;
        }
        if (jedis.sismember(userKey, String.valueOf(uid))) {
            logger.warn("该用户已抢购");
            jedis.close();
            return false;
        }

        jedis.watch(stockKey);
        if (Integer.parseInt(jedis.get(stockKey)) > 0) {
            Transaction transaction = jedis.multi();
            transaction.decr(stockKey);
            transaction.sadd(userKey, String.valueOf(uid));
            List<Object> result = transaction.exec();
//            jedis.decr(stockKey);
//            jedis.sadd(userKey, String.valueOf(uid));
            if (result.size() == 0) {
                logger.warn("抢购失败");
                jedis.close();
                return false;
            } else {
                logger.info(uid + " 抢购成功");
                jedis.close();
                return true;
            }
        }


    }
}
