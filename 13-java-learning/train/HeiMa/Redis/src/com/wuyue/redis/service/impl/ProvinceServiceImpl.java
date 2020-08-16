package com.wuyue.redis.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wuyue.redis.dao.ProvinceDao;
import com.wuyue.redis.dao.impl.ProvinceDaoImpl;
import com.wuyue.redis.domain.Province;
import com.wuyue.redis.service.ProvinceService;
import com.wuyue.redis.utils.JedisUtils;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @author DeltaV235
 * @version 1.0
 * @className ProvinceServiceImpl
 * @description
 * @date 2020/2/24 13:23
 */
public class ProvinceServiceImpl implements ProvinceService {
    private ProvinceDao dao = new ProvinceDaoImpl();

    @Override
    public List<Province> findAll() {
        return dao.findAll();
    }

    @Override
    public String findAllJson() {
        Jedis resource = JedisUtils.getResource();
        String province = resource.get("province");
        if (province == null || province.length() == 0) {
            System.out.println("redis中无数据，查询数据库");
            List<Province> list = dao.findAll();
            try {
                String json = new ObjectMapper().writeValueAsString(list);
                resource.set("province", json);
                return json;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("redis中存在数据");
        }
        return province;
    }
}
