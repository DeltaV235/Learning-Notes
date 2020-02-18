package com.wuyue.case17.dao;

import com.wuyue.case17.entities.User;

import java.util.List;

/**
 * @author DeltaV235
 * @version 1.0
 * @className UserDao
 * @description UserDaoImpl的接口，对数据库CRUD的一个标准接口
 * @date 2020/2/18 17:23
 */
public interface UserDao {
    /**
     * @return 存储整张user表的List
     * @author DeltaV235
     * @date 2020/2/18 19:52
     * @description 查找所有用户的所有字段信息
     */
    List<User> findAll();

    /**
     * @param user 添加的user对象
     * @return 添加成功则返回true，否则返回false
     * @author DeltaV235
     * @date 2020/2/18 19:53
     * @description 向user表中添加一条记录
     */
    boolean addUser(User user);

    /**
     * @param id 要删除的用户id
     * @return 若删除成功则返回true，否则返回false
     * @author DeltaV235
     * @date 2020/2/18 21:23
     * @description 指定user的id，删除指定的用户记录
     */
    boolean deleteUser(int id);

    /**
     * @param user 要修改的用户信息，根据其中封装的id信息来进行修改
     * @return 若修改成功则返回true，否则返回false
     * @author DeltaV235
     * @date 2020/2/18 21:51
     * @description 修改指定id的用户信息
     */
    boolean updateUser(User user);

    /**
     * @param field 查找的字段名
     * @param value 查找的值
     * @return 符合要求的一条记录的User封装对象
     * @author DeltaV235
     * @date 2020/2/18 23:54
     * @description 根据一个指定的键值对，在数据库中查找第一个符合要求的一条记录，并封装为User对象返回
     */
    User findUser(String field, Object value);
}
