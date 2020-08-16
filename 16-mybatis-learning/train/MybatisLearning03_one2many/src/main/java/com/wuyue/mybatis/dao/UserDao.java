package com.wuyue.mybatis.dao;

import com.wuyue.mybatis.domain.User;

import java.util.List;

/**
 * @author DeltaV235
 * @version 1.0
 * @className UserDao
 * @description
 * @date 2020/2/28 17:57
 */
public interface UserDao {
    /**
     * @return
     * @author DeltaV235
     * @date 2020/2/28 17:59
     * @description 查找user表中的所有数据
     */
    List<User> findAll();

    /**
     * @param uid
     * @author DeltaV235
     * @date 2020/2/29 14:00
     * @description 根据用户id查询一个用户
     */
    User findById(Integer uid);

    /**
     * @param username
     * @return
     * @author DeltaV235
     * @date 2020/2/29 14:04
     * @description 根据用户名称模糊查询
     */
    List<User> findByName(String username);

    /**
     * @param id
     * @return
     * @author DeltaV235
     * @date 2020/3/7 16:27
     * @description 通过id查找用户并将所有account封装至List中
     */
    User findByIdReturnAccountList(Integer id);
}




















