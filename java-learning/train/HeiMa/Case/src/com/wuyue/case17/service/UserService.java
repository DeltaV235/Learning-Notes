package com.wuyue.case17.service;

import com.wuyue.case17.dao.entities.User;

import java.util.List;

/**
 * @author DeltaV235
 * @version 1.0
 * @className UserService
 * @description UserServiceImpl的接口，用于处理User相关的业务逻辑
 * @date 2020/2/18 18:09
 */
public interface UserService {
    /**
     * @return 封装了User对象的List集合，User对象中封装了一条用户记录
     * @author DeltaV235
     * @date 2020/2/18 18:12
     * @description 查找所有用户的所有字段信息
     */
    List<User> findAll();
}
