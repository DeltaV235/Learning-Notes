package com.wuyue.test;

import com.wuyue.dao.UserDao;
import com.wuyue.domain.User;
import com.wuyue.mybatis.io.Resources;
import com.wuyue.mybatis.sqlsession.SqlSession;
import com.wuyue.mybatis.sqlsession.SqlSessionFactory;
import com.wuyue.mybatis.sqlsession.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author DeltaV235
 * @version 1.0
 * @className MybatisTest
 * @description Mybatis入门案例
 * @date 2020/2/25 22:32
 */
public class MybatisTest {
    public static void main(String[] args) throws IOException {
        // 1.读取配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(is);
        // 3.使用工厂生产SqlSession对象
        SqlSession session = factory.openSession();
        // 4.使用SqlSession创建Dao接口的代理对象
        UserDao userDao = session.getMapper(UserDao.class);
        // 5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        // 6.释放资源
        session.close();
        is.close();
    }
}
