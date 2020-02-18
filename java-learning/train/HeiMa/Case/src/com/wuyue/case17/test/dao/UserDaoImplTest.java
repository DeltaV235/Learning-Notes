package com.wuyue.case17.test.dao;

import com.wuyue.case17.dao.UserDao;
import com.wuyue.case17.dao.impl.UserDaoImpl;
import com.wuyue.case17.entities.User;
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

    /**
     * @author DeltaV235
     * @date 2020/2/18 20:06
     * @description 测试add方法是否能正常添加用户记录
     */
    @Test
    public void testAddUser() {
        User user = new User(null, "中月月", "男", 29, "日本", "87349134032",
                "yaho@live.com");
        userDao.addUser(user);
    }

    /**
     * @author DeltaV235
     * @date 2020/2/18 21:26
     * @description 测试delete方法是否能够正确删除指定用户信息
     */
    @Test
    public void testDeleteUser() {
        assert userDao.deleteUser(4);
    }

    @Test
    public void testUpdateUser() {
        User user = new User(1, "中月月", "男", 29, "日本", "87349134032",
                "yaho@live.com");
        assert userDao.updateUser(user);
    }

    /**
     * @author DeltaV235
     * @date 2020/2/19 0:41
     * @description 测试findUser能够返回正确地User对象
     */
    @Test
    public void testFindUser() {
//        User user = userDao.findUser("id", 1);
        User user = userDao.findUser("name", "呵呵");
        System.out.println(user);
    }
}
