package com.wuyue.case17.service;

import com.wuyue.case17.dao.UserDao;
import com.wuyue.case17.dao.UserDaoImpl;
import com.wuyue.case17.dao.entities.User;

import java.util.List;

/**
 * @author DeltaV235
 * @version 1.0
 * @className UserServiceImpl
 * @description 实行UserService接口，查询所用用户的数据
 * @date 2020/2/18 18:12
 */
public class UserServiceImpl implements UserService {

    /**
     * @author DeltaV235
     * @date 2020/2/18 18:15
     * @see UserService
     */
    @Override
    public List<User> findAll() {
        UserDao userDao = new UserDaoImpl();
        return userDao.findAll();
    }
}
