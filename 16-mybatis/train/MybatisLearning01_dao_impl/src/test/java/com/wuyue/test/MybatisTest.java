package com.wuyue.test;

import com.wuyue.dao.UserDao;
import com.wuyue.dao.UserDaoImpl;
import com.wuyue.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

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
        UserDao userDao = new UserDaoImpl(factory);
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        // 6.释放资源
        is.close();
    }
}
