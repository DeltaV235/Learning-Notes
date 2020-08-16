package com.wuyue.mybatis.defaults;

import com.wuyue.mybatis.cfg.Configuration;
import com.wuyue.mybatis.proxy.MapperProxy;
import com.wuyue.mybatis.sqlsession.SqlSession;
import com.wuyue.mybatis.utils.DataSourceUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author DeltaV235
 * @version 1.0
 * @className DefaultSqlSession
 * @description SqlSession的默认实现类，通过getMapper返回一个代理对象，代理Dao接口类，扩展一个selectList()方法
 * @date 2020/2/27 23:43
 */
public class DefaultSqlSession implements SqlSession {
    private Configuration cfg;
    private Connection conn;

    public DefaultSqlSession(Configuration cfg) {
        this.cfg = cfg;
        conn = DataSourceUtil.getSource(cfg);
    }

    @Override
    public <T> T getMapper(Class<T> targetClass) {
        return (T) Proxy.newProxyInstance(targetClass.getClassLoader(), new Class[]{targetClass},
                new MapperProxy(cfg, conn));
    }

    @Override
    public void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
