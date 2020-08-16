package com.wuyue.case17.service;

import com.wuyue.case17.entities.PageBean;
import com.wuyue.case17.entities.User;

import java.util.List;
import java.util.Map;

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

    /**
     * @param loginUser 登录用户用户名和密码封装成的User对象
     * @return 若用户合法则返回true，否则返回false
     * @author DeltaV235
     * @date 2020/2/19 17:21
     * @description 判断登录的用户是否合法
     */
    boolean isLegal(User loginUser);

    /**
     * @param checkCode      session中保存的验证码
     * @param inputCheckCode 用户输入的验证码
     * @return 若用户输入验证码与session中保存的验证码相同，则返回true，否则返回false
     * @author DeltaV235
     * @date 2020/2/19 17:29
     * @description 判断用户输入的验证码是否正确
     */
    boolean isCheckCodeLegal(String checkCode, String inputCheckCode);

    /**
     * @param user 将要写入数据库的User对象
     * @return 若合法数据则返回true，否则返回false
     * @author DeltaV235
     * @date 2020/2/19 22:04
     * @description 检查User对象是否符合要求
     */
    boolean checkUserData(User user);

    /**
     * @param ids 存储id的String数组
     * @return 返回删除记录的条数
     * @author DeltaV235
     * @date 2020/2/19 23:53
     * @description 批量删除指定id的用户信息
     */
    int batchDelUsers(String[] ids);

    /**
     * @param currentPage 当前显示的页数
     * @param rows        每页显示的行数
     * @return PageBean对象，封装了List<User>集合，总页数，总记录数等信息
     * @author DeltaV235
     * @date 2020/2/21 14:38
     * @description 通过http协议传回的currentPage和rows两个请求参数来封装一个PageBean对象
     */
    PageBean<User> findUserByPage(String currentPage, String rows);

    /**
     * @param paraMap     请求条件参数集合
     * @return 若查询到相关结果，则返回PageBean对象，否则返回一个list=null total=0 currentPage=1
     * rows=5 的PageBean对象
     * @author DeltaV235
     * @date 2020/2/21 17:08
     * @description 根据传入的条件参数集合和分页信息，返回对应的PageBean对象
     */
    PageBean<User> findUserByConditionAndPage(Map<String, String> paraMap);
}
