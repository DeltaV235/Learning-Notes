package com.wuyue.service;

import com.wuyue.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author DeltaV235
 * @version 1.0
 * @className BaseService
 * @description
 * @date 2020/3/14 2:07
 */
public abstract class BaseService<T> {
    @Autowired
    private BaseDao<T> baseDao;

    public void save() {
        baseDao.save();
    }
}














