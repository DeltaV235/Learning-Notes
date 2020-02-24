package com.wuyue.redis.dao;

import com.wuyue.redis.domain.Province;

import java.util.List;

/**
 * @author DeltaV235
 * @version 1.0
 * @className ProvinceDao
 * @description Province表相关数据库操作接口
 * @date 2020/2/24 13:20
 */
public interface ProvinceDao {
    List<Province> findAll();
}
