package com.wuyue.mybatis.sqlsession;

/**
 * @author DeltaV235
 * @version 1.0
 * @className SqlSessionFactory
 * @description
 * @date 2020/2/26 20:59
 */
public interface SqlSessionFactory {
    SqlSession openSession();
}
