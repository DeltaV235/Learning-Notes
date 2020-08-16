package dao.impl;

import dao.UserDao;
import domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author DeltaV235
 * @version 1.0
 * @className UserDaoImpl
 * @description
 * @date 2020/2/29 18:19
 */
public class UserDaoImpl implements UserDao {
    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<User> findAll() {
        SqlSession sqlSession = factory.openSession();
        return sqlSession.selectList("dao.UserDao.findAll");
    }

    @Override
    public void saveUser(User user) {

    }

    @Override
    public void deleteUser(Integer userId) {

    }

    @Override
    public User findById(Integer uid) {
        return null;
    }

    @Override
    public List<User> findByName(String username) {
        return null;
    }

    @Override
    public int countTotal() {
        return 0;
    }
}
