package com.wuyue.mybatis.sqlsession;

/**
 * @author DeltaV235
 * @version 1.0
 * @className SqlSession
 * @description
 * @date 2020/2/26 21:00
 */
public interface SqlSession {
    <T> T getMapper(Class<T> targetClass);

    void close();
}
