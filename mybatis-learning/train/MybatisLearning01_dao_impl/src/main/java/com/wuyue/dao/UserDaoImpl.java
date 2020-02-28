package com.wuyue.dao;

import com.wuyue.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author DeltaV235
 * @version 1.0
 * @className UserDaoImpl
 * @description
 * @date 2020/2/26 16:48
 */
public class UserDaoImpl implements UserDao {
    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<User> findAll() {
        SqlSession session = factory.openSession();
        List<User> result = session.selectList("com.wuyue.dao.UserDao.findAll");
        session.close();
        return result;
    }
}
