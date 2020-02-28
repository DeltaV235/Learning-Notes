package com.wuyue.mybatis.sqlsession;

import com.wuyue.mybatis.cfg.Configuration;
import com.wuyue.mybatis.defaults.DefaultSqlSessionFactory;
import com.wuyue.mybatis.utils.XMLConfigBuilder;

import java.io.InputStream;

/**
 * @author DeltaV235
 * @version 1.0
 * @className SqlSessionFactoryBuilder
 * @description 在此仅用于读取配置文件并创建SqlSessionFactory
 * @date 2020/2/26 20:58
 */
public class SqlSessionFactoryBuilder {
    public SqlSessionFactoryBuilder() {
    }

    public SqlSessionFactory build(InputStream inputStream) {
        Configuration cfg = XMLConfigBuilder.loadConfiguration(inputStream);
        return new DefaultSqlSessionFactory(cfg);
    }
}
