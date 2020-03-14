package com.wuyue.dao.impl;

import com.wuyue.dao.BaseDao;
import com.wuyue.domain.User;
import org.springframework.stereotype.Repository;

/**
 * @author DeltaV235
 * @version 1.0
 * @className UserDaoImpl
 * @description
 * @date 2020/3/14 2:03
 */
@Repository
public class UserDaoImpl extends BaseDao<User> {
    @Override
    public void save() {
        System.out.println("UserDaoImpl....save()");
    }
}














