package com.wuyue.mybatis.utils;

import com.wuyue.mybatis.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author DeltaV235
 * @version 1.0
 * @className DataSourceUtil
 * @description 通过Configuration对象，创建Connection对象并返回
 * @date 2020/2/28 1:31
 */
public class DataSourceUtil {
    public static Connection getSource(Configuration cfg) {
        try {
            return DriverManager.getConnection(cfg.getUrl(), cfg.getUsername(), cfg.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
