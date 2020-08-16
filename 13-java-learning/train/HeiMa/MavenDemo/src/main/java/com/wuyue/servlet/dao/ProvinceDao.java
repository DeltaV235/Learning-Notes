package com.wuyue.servlet.dao;

import com.wuyue.servlet.entities.Province;

import java.util.List;

/**
 * @author DeltaV235
 * @version 1.0
 * @className ProvinceDao
 * @description
 * @date 2020/2/25 1:28
 */
public interface ProvinceDao {
    List<Province> findAll();
}
