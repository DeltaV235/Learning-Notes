package com.wuyue.dao;

import com.wuyue.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author DeltaV235
 * @version 1.0
 * @className UserDao
 * @description User表的持久层接口
 * @date 2020/2/25 21:58
 */
public interface UserDao {
    /**
     * @return 用户对象集合
     * @author DeltaV235
     * @date 2020/2/25 22:01
     * @description 查询所有用户
     */
    @Select("select * from user")
    List<User> findAll();
}
