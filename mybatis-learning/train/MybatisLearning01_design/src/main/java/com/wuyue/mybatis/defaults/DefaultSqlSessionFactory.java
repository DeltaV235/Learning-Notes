package com.wuyue.mybatis.defaults;

import com.wuyue.mybatis.cfg.Configuration;
import com.wuyue.mybatis.sqlsession.SqlSession;
import com.wuyue.mybatis.sqlsession.SqlSessionFactory;

/**
 * @author DeltaV235
 * @version 1.0
 * @className DefaultSqlSessionFactory
 * @description 实现了SqlSessionFactory的默认工厂类，仅用于创建SQLSession对象
 * @date 2020/2/27 23:42
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private Configuration cfg;

    public DefaultSqlSessionFactory(Configuration cfg) {
        this.cfg = cfg;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(cfg);
    }
}
