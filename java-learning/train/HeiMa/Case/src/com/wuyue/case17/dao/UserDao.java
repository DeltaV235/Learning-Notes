package com.wuyue.case17.dao;

import com.wuyue.case17.entities.User;

import java.sql.SQLSyntaxErrorException;
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
     * @return 符合要求的一条记录的User封装对象，若为找到指定的user记录，则返回null
     * @author DeltaV235
     * @date 2020/2/18 23:54
     * @description 根据一个指定的键值对，在数据库中查找第一个符合要求的一条记录，并封装为User对象返回
     */
    User findUser(String field, Object value);

    /**
     * @param startIdx 查找开始的索引
     * @param rows     记录数
     * @return 符合条件的记录集合
     * @author DeltaV235
     * @date 2020/2/21 13:50
     * @description 根据分页信息(起始索引 查找多少条记录)来返回记录的集合
     */
    List<User> findUserByLimit(int startIdx, int rows);

    /**
     * @return 返回user表中的记录数
     * @author DeltaV235
     * @date 2020/2/21 13:59
     * @description 查找user表中的记录数
     */
    int findUserCount();

    /**
     * @param condKeys   条件字段名
     * @param condValues 条件值，condKeys.length需要等于condValues.length
     * @param startIdx   分页查询开始索引值
     * @param rows       分页查询每页显示记录数
     * @return 符合条件的User对象List集合
     * @throws SQLSyntaxErrorException 若传入的条件字段数组的长度与条件值数组的长度不一致，则抛出SQL语法错误异常
     * @author DeltaV235
     * @date 2020/2/21 16:33
     * @description 根据指定的条件和分页查询用户数据
     */
    List<User> findUserByConditionAndLimit(String[] condKeys, String[] condValues, int startIdx, int rows)
            throws SQLSyntaxErrorException;

    /**
     * @param condKeys   条件字段名
     * @param condValues 条件值
     * @return 符合条件的记录数量
     * @throws SQLSyntaxErrorException 若传入的条件字段数组的长度与条件值数组的长度不一致，则抛出SQL语法错误异常
     * @author DeltaV235
     * @date 2020/2/21 16:36
     * @description 查询符合条件的记录数
     */
    int findUserCountByCondition(String[] condKeys, String[] condValues) throws SQLSyntaxErrorException;
}
