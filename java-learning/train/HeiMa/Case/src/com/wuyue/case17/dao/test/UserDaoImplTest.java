package com.wuyue.case17.dao.test;

import com.wuyue.case17.dao.UserDao;
import com.wuyue.case17.dao.UserDaoImpl;
import com.wuyue.case17.dao.entities.User;
import org.junit.Test;

import java.util.List;

/**
 * @author DeltaV235
 * @version 1.0
 * @className UserDaoImplTest
 * @description 测试DaoImpl类的CRUD操作
 * @date 2020/2/18 17:52
 */
public class UserDaoImplTest {

    UserDao userDao = new UserDaoImpl();

    /**
     * @author DeltaV235
     * @date 2020/2/18 17:54
     * @description 测试findAll()是否能正确返回数据
     */
    @Test
    public void testFindAll() {
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
