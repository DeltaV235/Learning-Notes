package com.wuyue.mybatis.proxy;

import com.wuyue.mybatis.cfg.Configuration;
import com.wuyue.mybatis.cfg.Mapper;
import com.wuyue.mybatis.utils.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

/**
 * @author DeltaV235
 * @version 1.0
 * @className MapperProxy
 * @description Dao的代理对象的InvocationHandler对象，用于扩展真实对象的方法，由于真实对象是接口类，所以不需要引入真实对象，
 * 直接调用工具类Executor的selectList方法并返回resultType集合即可
 * @date 2020/2/28 1:21
 */
public class MapperProxy implements InvocationHandler {
    private Configuration cfg;
    private Connection conn;

    public MapperProxy(Configuration cfg, Connection conn) {
        this.cfg = cfg;
        this.conn = conn;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        String className = method.getDeclaringClass().getName();
        String fullName = className + "." + methodName;
        Map<String, Mapper> mappers = cfg.getMappers();
        Mapper mapper = mappers.get(fullName);
        return new Executor().selectList(mapper, conn);
    }
}
