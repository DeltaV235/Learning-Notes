package com.wuyue.redis.service;

import com.wuyue.redis.domain.Province;

import java.util.List;

/**
 * @author DeltaV235
 * @version 1.0
 * @className ProvinceService
 * @description Province相关操作的Service接口
 * @date 2020/2/24 13:22
 */
public interface ProvinceService {
    List<Province> findAll();

    String findAllJson();
}
