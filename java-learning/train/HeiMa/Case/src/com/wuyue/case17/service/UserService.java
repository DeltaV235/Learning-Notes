package com.wuyue.case17.service;

import com.wuyue.case17.entities.User;

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

    /**
     * @param user 由controller封装好的User对象
     * @return dao层添加成功则返回true，否则返回false
     * @author DeltaV235
     * @date 2020/2/18 20:11
     * @description 完成添加一个用户的业务功能
     */
    boolean addUser(User user);

    /**
     * @param id 用户id
     * @return 删除成功则返回true，否则返回false
     * @author DeltaV235
     * @date 2020/2/18 21:29
     * @description 提供删除的ID，删除指定的用户
     */
    boolean deleteUser(int id);

    /**
     * @param user 将要修改为的数据封装
     * @return 修改成功则返回true，否则返回false
     * @author DeltaV235
     * @date 2020/2/19 0:43
     * @description 通过传入User对象，调用UserDao，修改user中id字段值相同的记录数据
     */
    boolean updateUser(User user);

    /**
     * @param id 用户id
     * @return 若查询到对应的记录，则封装为User返回，否则返回null
     * @author DeltaV235
     * @date 2020/2/19 0:46
     * @description 通过id获取用户信息，并封装为User对象返回
     */
    User findUser(int id);
}
