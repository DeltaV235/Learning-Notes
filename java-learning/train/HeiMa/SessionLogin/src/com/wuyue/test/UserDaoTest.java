package com.wuyue.test;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import com.wuyue.entities.User;
import com.wuyue.UserDao;

import java.lang.reflect.InvocationTargetException;

/**
 * @author DeltaV235
 * @version 1.0
 * @className UserDaoTest
 * @description 测试类
 * @date 2020/2/17 0:57
 */
public class UserDaoTest {

    @Test
    public void test1() throws InvocationTargetException, IllegalAccessException {
        UserDao userDao = new UserDao();
        User loginUser = new User();
        BeanUtils.setProperty(loginUser, "username", "吴越");
        BeanUtils.setProperty(loginUser, "password", "7861");
        User user = userDao.login(loginUser);
        System.out.println(user);
    }
}
